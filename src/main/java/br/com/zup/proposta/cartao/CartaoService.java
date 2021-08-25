package br.com.zup.proposta.cartao;

import br.com.zup.proposta.avisoviagem.AvisoRequest;
import br.com.zup.proposta.avisoviagem.AvisoRequestApi;
import br.com.zup.proposta.avisoviagem.AvisoStatusResponseApi;
import br.com.zup.proposta.bloqueiocartao.*;
import br.com.zup.proposta.cartao.entity.Cartao;
import br.com.zup.proposta.cartao.response.CartaoGeradoResponse;
import br.com.zup.proposta.exception.CartaoNotFoundException;
import br.com.zup.proposta.exception.BloqueioNotValidException;
import br.com.zup.proposta.exception.SystemNotAvailableException;
import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.zup.proposta.cartao.StatusCartao.*;
import static br.com.zup.proposta.novaproposta.EstadoProposta.APROVADO;
import static br.com.zup.proposta.novaproposta.EstadoProposta.ELEGIVEL;

@Service
public class CartaoService {

    private PropostaRepository propostaRepository;
    private GeradorCartaoApiExterna cartaoApiExterna;
    private CartaoRepository cartaoRepository;
    private BloqueioRepository bloqueioRepository;

    public CartaoService(PropostaRepository propostaRepository, GeradorCartaoApiExterna cartaoApiExterna, CartaoRepository cartaoRepository, BloqueioRepository bloqueioRepository) {
        this.propostaRepository = propostaRepository;
        this.cartaoApiExterna = cartaoApiExterna;
        this.cartaoRepository = cartaoRepository;
        this.bloqueioRepository = bloqueioRepository;
    }

    @Scheduled(fixedDelayString = "${proposta.cartao.scheduled.fixeddelay}", initialDelayString = "${proposta.cartao.scheduled.initialdelay}")
    @Transactional
    public void executarOperacao() {
        List<Proposta> propostasElegiveis = propostaRepository.findAllByEstado(ELEGIVEL);
        for (Proposta proposta : propostasElegiveis) {
            CartaoGeradoResponse response = null;
            try {
                response = cartaoApiExterna.getCartaoGeradoResponse(proposta.getId());
                getNumeroCartao(proposta, response);

            } catch (FeignException e) {
                e.getMessage();
            }

        }
    }

    public void getNumeroCartao(Proposta proposta, CartaoGeradoResponse response) {
        String numeroCartao = response.getId();
        if (numeroCartao != null) {
            proposta.insereNumeroCartao(numeroCartao);
            proposta.atualizaEstado(APROVADO);
            propostaRepository.save(proposta);
            Cartao cartao = new Cartao(response);
            cartaoRepository.save(cartao);
        }
    }

    @Scheduled(fixedDelay = 500000, initialDelay = 10000)
    @Transactional
    public void atualizarBaseDeCartoes(){
        List<Cartao> cartoes = cartaoRepository.findAll();
        for (Cartao cartao: cartoes) {
            CartaoGeradoResponse response = cartaoApiExterna.getCartaoGeradoResponse(cartao.getNumero());
            Cartao toCartao = cartaoRepository.findByNumero(response.getId());


        }
        System.out.println("Atualizando Base de cartoes Local");
    }

    public boolean verificadorDeCartao(String cartaoId) throws CartaoNotFoundException {
        if(cartaoRepository.existsByNumero(cartaoId)){
            return true;
        }
        throw new CartaoNotFoundException(cartaoId);
    }



    public void bloqueadorDeCartao(String idCartao, BloqueioRequest request, String ip){

        boolean existsCartao = verificadorDeCartao(idCartao);
        Cartao cartao = cartaoRepository.findByNumero(idCartao);
        boolean isBloqueadoApi =  verificaBloqueioApiExterna(idCartao);
        boolean isBloqueadoBD = verificaBloqueioBancoDeDados(cartao);

        if (isBloqueadoApi || isBloqueadoBD){
            throw new BloqueioNotValidException("Existe um bloqueio ativo para este cartão");
        }

        bloquearCartaoApiExterna(idCartao, request);
        bloqueiaCartaoBDLocal(idCartao, ip, existsCartao, cartao, isBloqueadoBD);
    }

    private void bloqueiaCartaoBDLocal(String idCartao, String ip, boolean existsCartao, Cartao cartao, boolean isBloqueadoBD) {
        if ( existsCartao && !isBloqueadoBD){
            BloqueioAtivoResponse bloqueioAtivoResponse = getBloqueioAtivoApi(idCartao);
            cartao.getBloqueio().add(new Bloqueio(bloqueioAtivoResponse, ip, cartao));
            if (DESBLOQUEADO.equals(cartao.getStatus())){
                cartao.atualizaStatusCartao();
                cartaoRepository.save(cartao);
            }else if (BLOQUEADO.equals(cartao.getStatus())){
                cartaoRepository.save(cartao);
            }

        }
    }
    /**
     * Método que realiza a chamada para API e bloqueia o cartão
     * @param idCartao
     * @param request
     */
    private void bloquearCartaoApiExterna(String idCartao, BloqueioRequest request) {
        BloqueioResponse response = null;
        try {
            response = cartaoApiExterna.getBloqueio(idCartao, request);


        } catch (FeignException e) {
            if(e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()){
                throw new BloqueioNotValidException("Existe um bloqueio ativo para este cartão");
            }
        }
    }


    public boolean verificaBloqueioBancoDeDados(Cartao cartao) {
        boolean isBloqueado = false;
        try {
            isBloqueado = bloqueioRepository.existsByCartaoAndAtivoTrue(cartao.getId());//Ativo é bloqueio ativo
        } catch (RuntimeException e) {
            return isBloqueado;
        }
        return isBloqueado;
    }


    private boolean verificaBloqueioApiExterna(String idCartao) {
        CartaoGeradoResponse geradoResponse = cartaoApiExterna.getCartaoGeradoResponse(idCartao);
        List<BloqueioAtivoResponse> ativoResponseList = geradoResponse.getBloqueios();
        boolean isBloqueado = false;
        for (BloqueioAtivoResponse bloqueio: ativoResponseList) {
            if (bloqueio.isAtivo()){
                return bloqueio.isAtivo();
            }
        }
        return isBloqueado;
    }

    private BloqueioAtivoResponse getBloqueioAtivoApi(String idCartao){
        CartaoGeradoResponse geradoResponse = cartaoApiExterna.getCartaoGeradoResponse(idCartao);
        List<BloqueioAtivoResponse> ativoResponseList = geradoResponse.getBloqueios();
        for (BloqueioAtivoResponse bloqueio: ativoResponseList) {
            if (bloqueio.isAtivo()){
                return bloqueio;
            }
        }
        throw new BloqueioNotValidException("Não existe bloqueio ativo");
    }


    public boolean cadastrarAvisoViagemApi(String id, AvisoRequest  request) {


        LocalDate date = request.getValidoAte();

        AvisoRequestApi avisoRequestApi = new AvisoRequestApi(request.getDestino(), date);
        try {
            AvisoStatusResponseApi avisoStatusResponseApi = cartaoApiExterna.getAvisoApi(id, avisoRequestApi);
            if(avisoStatusResponseApi.getResultado().equals("CRIADO")){
                return true;
            }
            throw new SystemNotAvailableException("Erro ao tentar cadastrar aviso");
        } catch (FeignException e) {
            throw new SystemNotAvailableException("Erro ao tentar cadastrar aviso");
        }
    }
}


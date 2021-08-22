package br.com.zup.proposta.cartao;

import br.com.zup.proposta.bloqueiocartao.*;
import br.com.zup.proposta.cartao.entity.Cartao;
import br.com.zup.proposta.cartao.response.CartaoGeradoResponse;
import br.com.zup.proposta.exception.CartaoNotFoundException;
import br.com.zup.proposta.exception.BloqueioNotValidException;
import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.util.List;

import static br.com.zup.proposta.novaproposta.EstadoProposta.APROVADO;
import static br.com.zup.proposta.novaproposta.EstadoProposta.ELEGIVEL;

@Service
public class CartaoService {

    private PropostaRepository propostaRepository;
    private GeradorCartaoApiExterna cartaoApiExterna;
    private CartaoRepository cartaoRepository;
    private BloqueioRepository bloqueioRepository;

    public CartaoService(PropostaRepository propostaRepository, GeradorCartaoApiExterna cartaoApiExterna, CartaoRepository cartaoRepository) {
        this.propostaRepository = propostaRepository;
        this.cartaoApiExterna = cartaoApiExterna;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelayString = "${proposta.cartao.scheduled.fixeddelay}", initialDelayString = "${proposta.cartao.scheduled.initialdelay}")
    @Transactional
    private void executarOperacao() {
        System.out.println("Rodando gerador de cartão");
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

    private void getNumeroCartao(Proposta proposta, CartaoGeradoResponse response) {
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
    private void atualizarBaseDeCartoes(){
        List<Cartao> cartoes = cartaoRepository.findAll();
        for (Cartao cartao: cartoes) {
            CartaoGeradoResponse response = cartaoApiExterna.getCartaoGeradoResponse(cartao.getNumero());
            Cartao toCartao = cartaoRepository.findByNumero(response.getId());
            //Colocar aqui as atualizações de cartão

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
        boolean isBloqueado =  verificaBloqueioApiExterna(idCartao);
        boolean isBloqueadoBD = verificaBloqueioBancoDeDados(cartao);

        if (isBloqueado || isBloqueadoBD){
            throw new BloqueioNotValidException("Existe um bloqueio ativo para este cartão");
            //Se não houver bloqueio na API o bd deve ser atualizado.
        }

        BloqueioResponse response = null;
        try {
            response = cartaoApiExterna.getBloqueio(idCartao, request);

        } catch (FeignException e) {
            if(e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()){
                throw new BloqueioNotValidException("Existe um bloqueio ativo para este cartão");
            }
        }
        String resultado = response.getResultado();
        if (!isBloqueado && existsCartao){
            BloqueioAtivoResponse ativoResponse = getBloqueiAtivoMethod(idCartao);
            cartao.getBloqueio().add(new Bloqueio(ativoResponse, ip, cartao));
            cartaoRepository.save(cartao);

        }

    }


    private boolean verificaBloqueioBancoDeDados(Cartao cartao) {
        boolean isBloqueado = false;
        try {
            isBloqueado = bloqueioRepository.existsByCartaoAndAtivoTrue(cartao.getId());
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

    private BloqueioAtivoResponse getBloqueiAtivoMethod(String idCartao){
        CartaoGeradoResponse geradoResponse = cartaoApiExterna.getCartaoGeradoResponse(idCartao);
        List<BloqueioAtivoResponse> ativoResponseList = geradoResponse.getBloqueios();
        for (BloqueioAtivoResponse bloqueio: ativoResponseList) {
            if (bloqueio.isAtivo()){
                return bloqueio;
            }
        }
        throw new BloqueioNotValidException("Não existe bloqueio ativo");
    }




}


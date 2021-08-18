package br.com.zup.proposta.gerarcartao;

import br.com.zup.proposta.gerarcartao.response.CartaoGeradoResponse;
import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.PropostaRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.util.List;

import static br.com.zup.proposta.novaproposta.EstadoProposta.APROVADO;
import static br.com.zup.proposta.novaproposta.EstadoProposta.ELEGIVEL;

@Component
public class GerarCartao {

    PropostaRepository propostaRepository;
    GeradorCartaoApiExterna cartaoApiExterna;

    public GerarCartao(PropostaRepository propostaRepository, GeradorCartaoApiExterna cartaoApiExterna) {
        this.propostaRepository = propostaRepository;
        this.cartaoApiExterna = cartaoApiExterna;
    }

    @Scheduled(fixedDelay = 30000)
    @Transactional
    private void executarOperacao() {
        List<Proposta> propostasElegiveis = propostaRepository.findAllByEstado(ELEGIVEL);
        for (Proposta proposta : propostasElegiveis) {
            GerarCartaoRequest cartaoRequest = new GerarCartaoRequest(proposta);
            CartaoGeradoResponse response = null;
            try {
                response = cartaoApiExterna.getCartaoGeradoResponse(cartaoRequest);
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
        }
    }


}


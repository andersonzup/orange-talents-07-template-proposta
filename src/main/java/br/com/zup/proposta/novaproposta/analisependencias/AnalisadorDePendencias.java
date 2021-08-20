package br.com.zup.proposta.novaproposta.analisependencias;


import br.com.zup.proposta.gerarcartao.GeradorCartaoApiExterna;
import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.PropostaRepository;
import br.com.zup.proposta.novaproposta.consultarestricao.EstadoPropostaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

import static br.com.zup.proposta.novaproposta.EstadoProposta.AGUARDANDO_APROVACAO;


@Component
public class AnalisadorDePendencias {

    PropostaRepository propostaRepository;
    GeradorCartaoApiExterna cartaoApiExterna;
    EstadoPropostaService estadoPropostaService;

    public AnalisadorDePendencias(PropostaRepository propostaRepository, GeradorCartaoApiExterna cartaoApiExterna, EstadoPropostaService estadoPropostaService) {
        this.propostaRepository = propostaRepository;
        this.cartaoApiExterna = cartaoApiExterna;
        this.estadoPropostaService = estadoPropostaService;
    }

    @Scheduled(fixedDelayString = "${proposta.pendencias.scheduled.fixeddelay}", initialDelayString = "${proposta.pendencias.scheduled.initialdelay}")
    @Transactional
    private void analisarPendencias(){
        List<Proposta> propostas = propostaRepository.findAllByEstado(AGUARDANDO_APROVACAO);
        System.out.println("Rodando o analisar de pendencias");
        for (Proposta proposta : propostas){
            estadoPropostaService.getEstadoProposta(proposta);
        }
    }
}
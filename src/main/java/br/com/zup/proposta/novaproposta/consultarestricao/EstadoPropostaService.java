package br.com.zup.proposta.novaproposta.consultarestricao;

import br.com.zup.proposta.novaproposta.EstadoProposta;
import br.com.zup.proposta.novaproposta.Proposta;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EstadoPropostaService {

    private ConsultaRestricaoApiExterna consultaRestricaoApiExterna;

    public EstadoPropostaService(ConsultaRestricaoApiExterna consultaRestricaoApiExterna) {
        this.consultaRestricaoApiExterna = consultaRestricaoApiExterna;
    }

    public EstadoProposta getEstadoProposta(Proposta proposta) {
        ConsultaRestricaoRequest consultaRestricaoRequest = new ConsultaRestricaoRequest(proposta);
        ConsultaRestricaoResponse response = null;
        try {
            response = consultaRestricaoApiExterna.getPropostaResponse(consultaRestricaoRequest);
            proposta.atualizaEstado(EstadoProposta.ELEGIVEL);
            return EstadoProposta.ELEGIVEL;

        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()){
                proposta.atualizaEstado(EstadoProposta.NAO_ELEGIVEL);
                return EstadoProposta.NAO_ELEGIVEL;
            }
            return EstadoProposta.AGUARDANDO_APROVACAO;
        }

    }
}

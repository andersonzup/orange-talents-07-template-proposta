package br.com.zup.proposta.novaproposta.consultarestricao;

import br.com.zup.proposta.novaproposta.EstadoProposta;
import br.com.zup.proposta.novaproposta.Proposta;
import org.springframework.stereotype.Service;

@Service
public class EstadoPropostaService {

    private ConsultaRestricaoApiExterna consultaRestricaoApiExterna;

    public EstadoPropostaService(ConsultaRestricaoApiExterna consultaRestricaoApiExterna) {
        this.consultaRestricaoApiExterna = consultaRestricaoApiExterna;
    }

    public EstadoProposta getEstadoProposta(Proposta proposta) {
        ConsultaRestricaoRequest consultaRestricaoRequest = new ConsultaRestricaoRequest(proposta);
        ConsultaRestricaoResponse response = consultaRestricaoApiExterna.getPropostaResponse(consultaRestricaoRequest);
        String resultadoSolicitacao = response.getResultadoSolicitacao();

        if (resultadoSolicitacao.equals("COM_RESTRICAO")){
            proposta.atualizaEstado(EstadoProposta.NAO_ELEGIVEL);
            return EstadoProposta.NAO_ELEGIVEL;
        }
        proposta.atualizaEstado(EstadoProposta.ELEGIVEL);
        return EstadoProposta.ELEGIVEL;
    }
}

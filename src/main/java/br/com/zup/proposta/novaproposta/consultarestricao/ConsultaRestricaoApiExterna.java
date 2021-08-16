package br.com.zup.proposta.novaproposta.consultarestricao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "consultaRestricao", url = "http://localhost:9999/api/solicitacao")
public interface ConsultaRestricaoApiExterna {

    @RequestMapping(method = RequestMethod.POST)
    ConsultaRestricaoResponse getPropostaResponse(ConsultaRestricaoRequest consultaRestricaoRequest);
}

package br.com.zup.proposta.novaproposta.consultarestricao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${proposta.apiexterna.consulta.nome}", url = "${proposta.apiexterna.consulta.url}")
public interface ConsultaRestricaoApiExterna {

    @RequestMapping(method = RequestMethod.POST)
    ConsultaRestricaoResponse getPropostaResponse(ConsultaRestricaoRequest consultaRestricaoRequest);
}

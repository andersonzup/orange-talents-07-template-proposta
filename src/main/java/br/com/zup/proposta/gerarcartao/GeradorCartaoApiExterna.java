package br.com.zup.proposta.gerarcartao;


import br.com.zup.proposta.gerarcartao.response.CartaoGeradoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "geradorCartao", url = "http://localhost:8888/api/cartoes")
public interface GeradorCartaoApiExterna {

    @RequestMapping(method = RequestMethod.POST)
    CartaoGeradoResponse getCartaoGeradoResponse(GerarCartaoRequest gerarCartaoRequest);
}

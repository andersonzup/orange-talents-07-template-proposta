package br.com.zup.proposta.gerarcartao;


import br.com.zup.proposta.gerarcartao.response.CartaoGeradoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${proposta.apiexterna.cartao.nome}", url = "${proposta.apiexterna.cartao.url}")
public interface GeradorCartaoApiExterna {

    @GetMapping
    CartaoGeradoResponse getCartaoGeradoResponse(@RequestParam Long idProposta);

    @GetMapping(path = "/{id}")
    CartaoGeradoResponse getCartaoGeradoResponse(@PathVariable String id);
}

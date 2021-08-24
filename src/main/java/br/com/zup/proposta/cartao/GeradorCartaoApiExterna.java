package br.com.zup.proposta.cartao;


import br.com.zup.proposta.avisoviagem.AvisoRequest;
import br.com.zup.proposta.avisoviagem.AvisoRequestApi;
import br.com.zup.proposta.avisoviagem.AvisoStatusResponseApi;
import br.com.zup.proposta.bloqueiocartao.BloqueioRequest;
import br.com.zup.proposta.bloqueiocartao.BloqueioAtivoResponse;
import br.com.zup.proposta.bloqueiocartao.BloqueioResponse;
import br.com.zup.proposta.cartao.response.CartaoGeradoResponse;
import br.com.zup.proposta.paypal.CarteiraRequest;
import br.com.zup.proposta.paypal.CarteiraResponseApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${proposta.apiexterna.cartao.nome}", url = "${proposta.apiexterna.cartao.url}")//http://localhost:8888/api/cartoes
public interface GeradorCartaoApiExterna {

    @GetMapping
    CartaoGeradoResponse getCartaoGeradoResponse(@RequestParam Long idProposta);

    @GetMapping(path = "/{id}")
    CartaoGeradoResponse getCartaoGeradoResponse(@PathVariable String id);

    @PostMapping(path = "/{id}/bloqueios")
    BloqueioResponse getBloqueio(@PathVariable String id, BloqueioRequest request);

    @PostMapping(path = "/{id}/bloqueios")
    BloqueioAtivoResponse getBloqueioAtivo(@PathVariable String id, BloqueioRequest request);

    @PostMapping(path = "/{id}/avisos")
    AvisoStatusResponseApi getAvisoApi(@PathVariable String id, AvisoRequestApi AvisoRequest);

    @PostMapping(path = "/{id}/carteiras")
    CarteiraResponseApi getCarteira(@PathVariable String id, CarteiraRequest carteiraRequest);


}

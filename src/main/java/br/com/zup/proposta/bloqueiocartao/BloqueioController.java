package br.com.zup.proposta.bloqueiocartao;

import br.com.zup.proposta.cartao.CartaoService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;

@RestController
@RequestMapping(path = "/api/v1/bloqueios")
public class BloqueioController {

    CartaoService cartaoService;
    BloqueioRepository bloqueioRepository;
    HttpServletRequest servletRequest;

    public BloqueioController(CartaoService cartaoService, BloqueioRepository bloqueioRepository, HttpServletRequest servletRequest) {
        this.cartaoService = cartaoService;
        this.bloqueioRepository = bloqueioRepository;
        this.servletRequest = servletRequest;
    }

    @GetMapping(path = "/{id}")
    public String bloquearCartao(@PathVariable("id") String id, @RequestHeader("USER-AGENT") String userAgent) throws MalformedURLException {
        validacaoURL(id);
        @Valid
        BloqueioRequest request = new BloqueioRequest(userAgent);
        cartaoService.bloqueadorDeCartao(id, request, capturaIp());
        return "Cartão bloqueado";
    }

    private String capturaIp() {
        String ipAddress = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = servletRequest.getRemoteAddr();
            return ipAddress;
        }
        return "0.0.0.0";
    }

    private void validacaoURL(String id) throws MalformedURLException {
        if(id.length() < 19){
            throw new MalformedURLException("Número do cartão inválido");
        }
    }


}

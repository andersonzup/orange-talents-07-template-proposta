package br.com.zup.proposta.bloqueiocartao;

import br.com.zup.proposta.cartao.CartaoService;
import br.com.zup.proposta.utils.UtilMethods;
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
        String ipAddress = UtilMethods.validaIp(servletRequest.getHeader("X-FORWARDED-FOR"), servletRequest);
        UtilMethods.validacaoCartaoId(id);
        @Valid
        BloqueioRequest request = new BloqueioRequest(userAgent);
        cartaoService.bloqueadorDeCartao(id, request, ipAddress);
        return "Cartão bloqueado";
    }



}

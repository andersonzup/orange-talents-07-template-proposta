package br.com.zup.proposta.avisoviagem;


import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.CartaoService;
import br.com.zup.proposta.cartao.entity.Cartao;
import br.com.zup.proposta.utils.UtilMethods;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.MalformedURLException;

@RestController
@RequestMapping(path = "/api/v1/avisos")
public class AvisoViagemController {

    private HttpServletRequest servletRequest;
    private CartaoService cartaoService;
    private CartaoRepository cartaoRepository;

    public AvisoViagemController(HttpServletRequest servletRequest, CartaoService cartaoService, CartaoRepository cartaoRepository) {
        this.servletRequest = servletRequest;
        this.cartaoService = cartaoService;
        this.cartaoRepository = cartaoRepository;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(path = "/{id}")
    @Transactional
    public String avisarViagem(@PathVariable("id") String id, @RequestBody @Valid AvisoRequest request,
                               @RequestHeader("USER-AGENT") String userAgent) throws MalformedURLException {

        String ipAddress = UtilMethods.validaIp(servletRequest.getHeader("X-FORWARDED-FOR"), servletRequest);
        UtilMethods.validacaoCartaoId(id);
        CadastrarAvisoViagem(id, request, userAgent, ipAddress);
        return "Aviso criado com sucesso";
    }

    private void CadastrarAvisoViagem(String id, AvisoRequest request, String userAgent, String ipAddress) {
        cartaoService.verificadorDeCartao(id);
        Aviso aviso = new Aviso(request.getValidoAte(), request.getDestino(), ipAddress, userAgent);
        Cartao cartao =  cartaoRepository.findByNumero(id);
        cartaoService.cadastrarAvisoViagemApi(id, request);

        cartao.getAvisos().add(aviso);
        cartaoRepository.save(cartao);
    }


}

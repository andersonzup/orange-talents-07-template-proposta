package br.com.zup.proposta.carteira;


import br.com.zup.proposta.cartao.CartaoRepository;
import br.com.zup.proposta.cartao.CartaoService;
import br.com.zup.proposta.cartao.GeradorCartaoApiExterna;
import br.com.zup.proposta.cartao.entity.Cartao;
import br.com.zup.proposta.carteira.carteiras.ContratoCarteira;
import br.com.zup.proposta.carteira.carteiras.EnumCarteira;
import br.com.zup.proposta.exception.UnprocessableEntityException;
import br.com.zup.proposta.utils.UtilMethods;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/paypal")
public class AssociacaoCarteiraController {

    private CartaoService cartaoService;
    private CartaoRepository cartaoRepository;
    private GeradorCartaoApiExterna cartaoApiExterna;
    private CarteiraRepository carteiraRepository;



    public AssociacaoCarteiraController(CartaoService cartaoService, CartaoRepository cartaoRepository, GeradorCartaoApiExterna cartaoApiExterna, CarteiraRepository carteiraRepository) {
        this.cartaoService = cartaoService;
        this.cartaoRepository = cartaoRepository;
        this.cartaoApiExterna = cartaoApiExterna;
        this.carteiraRepository = carteiraRepository;
    }

    @PostMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<?> associarCarteira(@PathVariable("id") String id, @RequestBody @Valid CarteiraRequest carteiraRequest, UriComponentsBuilder componentsBuilder) throws MalformedURLException {
        UtilMethods.validacaoCartaoId(id);
        cartaoService.verificadorDeCartao(id);
        Cartao cartao = cartaoRepository.findByNumero(id);
        HttpStatus status = verificarCarteirasAssociadasBd(id, carteiraRequest.getCarteira(), cartao);

        CarteiraResponseApi responseApi = associarCarteiraApiExterna(id, new CarteiraRequest(carteiraRequest.getEmail(), carteiraRequest.getCarteira()));
        Carteira carteira = new Carteira(responseApi.getId(), carteiraRequest.getEmail(), carteiraRequest.getCarteira());
        cartao.getCarteiras().add(carteira);
        ContratoCarteira contratoCarteira = carteiraRequest.getCarteira().getContratoCarteira();
        return contratoCarteira.implementacaoCarteiras(componentsBuilder, carteira);

    }


    private HttpStatus verificarCarteirasAssociadasBd(String id, EnumCarteira carteiraAssociada, Cartao cartao) {
        List<Carteira> carteiras = cartao.getCarteiras();
        for (Carteira carteira : carteiras) {
            if (carteira.getEmissor().equals(carteiraAssociada)) {
                throw new UnprocessableEntityException("Carteira já está associada a este cartão");
            }
        }

        return HttpStatus.OK;
    }

    private CarteiraResponseApi associarCarteiraApiExterna(String id, CarteiraRequest carteiraRequest) {
        try {
            CarteiraResponseApi carteiraResponseApi = cartaoApiExterna.getCarteira(id, carteiraRequest);
            return carteiraResponseApi;

        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                throw new UnprocessableEntityException("Carteira já está associada a este cartão");
            }
            throw new UnprocessableEntityException("Erro ao associar carteira, tente novamente");
        }
    }


}

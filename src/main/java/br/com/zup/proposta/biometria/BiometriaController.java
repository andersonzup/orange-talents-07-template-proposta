package br.com.zup.proposta.biometria;

import br.com.zup.proposta.cartao.CartaoService;
import br.com.zup.proposta.exception.CartaoNotFoundException;
import br.com.zup.proposta.cartao.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/biometria")
public class BiometriaController {

    BiometriaRepository biometriaRepository;
    CartaoService cartaoService;

    public BiometriaController(BiometriaRepository biometriaRepository, CartaoService cartaoService) {
        this.biometriaRepository = biometriaRepository;
        this.cartaoService = cartaoService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "/{id}")
    public ResponseEntity<?> cadastrarBiometria(@RequestBody @Valid BiometriaRequest request, @PathVariable("id") String id, UriComponentsBuilder componentsBuilder){
        cartaoService.verificadorDeCartao(id);
        Biometria biometria = biometriaRepository.save(new Biometria(request, id));
        URI uri = componentsBuilder.path("/api/v1/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }




}

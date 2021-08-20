package br.com.zup.proposta.biometria;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/biometria")
public class BiometriaController {

    @PostMapping
    public void cadastrarBiometria(@RequestBody @Valid BiometriaRequest request){

    }
}

package br.com.zup.proposta.biometria;

import br.com.zup.proposta.validacao.Base64;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BiometriaRequest {

    @Base64
    @NotBlank
    private String biometria;

    @Deprecated
    public BiometriaRequest() {
    }

    public BiometriaRequest(String biometria) {
        this.biometria = biometria;
    }


    public String getBiometria() {
        return biometria;
    }

}

package br.com.zup.proposta.biometria;

import br.com.zup.proposta.validacao.Base64;

import java.time.LocalDateTime;

public class BiometriaRequest {
    private String idCartao;

    @Base64
    private String biometria;

    private LocalDateTime localDateTime = LocalDateTime.now();

    @Deprecated
    public BiometriaRequest() {
    }

    public BiometriaRequest(String idCartao, String biometria) {
        this.idCartao = idCartao;
        this.biometria = biometria;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public String getBiometria() {
        return biometria;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}

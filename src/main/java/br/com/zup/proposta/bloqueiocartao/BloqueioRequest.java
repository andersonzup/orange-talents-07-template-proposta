package br.com.zup.proposta.bloqueiocartao;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BloqueioRequest {

    @NotBlank
    private String sistemaResponsavel;

    @Deprecated
    public BloqueioRequest() {
    }

    public BloqueioRequest(String sistemaResponsavel, boolean ativo) {
        this.sistemaResponsavel = sistemaResponsavel;
    }


    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

}

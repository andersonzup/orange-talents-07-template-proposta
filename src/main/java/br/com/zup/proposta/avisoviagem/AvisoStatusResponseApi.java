package br.com.zup.proposta.avisoviagem;

import javax.validation.constraints.NotBlank;

public class AvisoStatusResponseApi {

    private String resultado;

    public AvisoStatusResponseApi() {
    }

    public AvisoStatusResponseApi(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}

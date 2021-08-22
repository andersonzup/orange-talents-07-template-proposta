package br.com.zup.proposta.bloqueiocartao;

import br.com.zup.proposta.cartao.entity.Cartao;

import java.time.LocalDateTime;

public class BloqueioResponse {

    private String resultado;

    @Deprecated
    public BloqueioResponse() {
    }

    public BloqueioResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }


}

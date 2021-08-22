package br.com.zup.proposta.cartao.response;

import java.time.LocalDateTime;

public class AvisoResponse {
    private LocalDateTime validoAte;
    private String destino;

    @Deprecated
    public AvisoResponse() {
    }

    public AvisoResponse(LocalDateTime validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}

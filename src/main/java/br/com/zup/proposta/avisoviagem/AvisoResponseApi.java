package br.com.zup.proposta.avisoviagem;

import java.time.LocalDateTime;

public class AvisoResponseApi {
    private LocalDateTime validoAte;
    private String destino;

    @Deprecated
    public AvisoResponseApi() {
    }

    public AvisoResponseApi(LocalDateTime validoAte, String destino) {
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

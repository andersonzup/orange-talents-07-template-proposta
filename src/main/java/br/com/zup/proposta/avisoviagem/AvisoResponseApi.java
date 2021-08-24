package br.com.zup.proposta.avisoviagem;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoResponseApi {
    private LocalDate validoAte;
    private String destino;

    @Deprecated
    public AvisoResponseApi() {
    }


    public AvisoResponseApi(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }


    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}

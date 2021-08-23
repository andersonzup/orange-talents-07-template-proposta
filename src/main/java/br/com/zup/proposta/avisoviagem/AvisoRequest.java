package br.com.zup.proposta.avisoviagem;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class AvisoRequest {

    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate terminaEm;

    @Deprecated
    public AvisoRequest() {
    }

    public AvisoRequest(String destino, LocalDate terminaEm) {
        this.destino = destino;
        this.terminaEm = terminaEm;
    }


    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminaEm() {
        return terminaEm;
    }

    @Override
    public String toString() {
        return "AvisoRequest{" +
                "destino='" + destino + '\'' +
                ", terminaEm=" + terminaEm +
                '}';
    }
}

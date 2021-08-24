package br.com.zup.proposta.avisoviagem;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    private LocalDate validoAte;

    @Deprecated
    public AvisoRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public void setValidoAte(LocalDate validoAte) {
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}

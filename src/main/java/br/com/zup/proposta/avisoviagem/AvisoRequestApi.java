package br.com.zup.proposta.avisoviagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class AvisoRequestApi {


    private String destino;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    @Deprecated
    public AvisoRequestApi() {
    }

    public AvisoRequestApi(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }


    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

}

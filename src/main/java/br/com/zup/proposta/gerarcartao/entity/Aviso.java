package br.com.zup.proposta.gerarcartao.entity;

import br.com.zup.proposta.gerarcartao.response.AvisoResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime validoAte;
    private String destino;


    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDateTime validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso(AvisoResponse response) {
        this.validoAte = response.getValidoAte();
        this.destino = response.getDestino();
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}

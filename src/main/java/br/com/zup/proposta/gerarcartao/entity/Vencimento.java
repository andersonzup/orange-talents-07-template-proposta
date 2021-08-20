package br.com.zup.proposta.gerarcartao.entity;

import br.com.zup.proposta.gerarcartao.response.VencimentoResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
public class Vencimento {

    private String idVencimento;
    private int dia;

    @Column(insertable = false, updatable = false)
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String id, int dia, LocalDateTime dataDeCriacao) {
        this.idVencimento = id;
        this.dia = dia;
    }

    public Vencimento(VencimentoResponse response) {
        this.idVencimento = response.getId();
        this.dia = response.getDia();
    }

    public String getIdVencimento() {
        return idVencimento;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}

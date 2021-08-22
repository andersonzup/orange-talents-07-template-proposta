package br.com.zup.proposta.cartao.entity;

import br.com.zup.proposta.cartao.response.VencimentoResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vencimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idVencimento;
    private int dia;

    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

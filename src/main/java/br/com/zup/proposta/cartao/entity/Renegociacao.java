package br.com.zup.proposta.cartao.entity;

import br.com.zup.proposta.cartao.response.RenegociacaoResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
public class Renegociacao {

    private String idRenegociacao;

    private int quantidade;

    private double valor;

    @Column(insertable = false, updatable = false)
    private LocalDateTime dataDeCriacao;


    @Deprecated
    public Renegociacao() {
    }

    public Renegociacao(String idRenegociacao, int quantidade, double valor, LocalDateTime dataDeCriacao) {
        this.idRenegociacao = idRenegociacao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Renegociacao(RenegociacaoResponse response) {
        this.idRenegociacao = response.getId();
        this.quantidade = response.getQuantidade();
        this.valor = response.getValor();
        this.dataDeCriacao = response.getDataDeCriacao();
    }

    public String getIdRenegociacao() {
        return idRenegociacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}

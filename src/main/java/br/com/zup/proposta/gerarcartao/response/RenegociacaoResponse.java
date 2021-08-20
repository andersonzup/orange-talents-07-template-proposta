package br.com.zup.proposta.gerarcartao.response;

import br.com.zup.proposta.gerarcartao.entity.Renegociacao;

import java.time.LocalDateTime;

public class RenegociacaoResponse {
    private String id;
    private int quantidade;
    private double valor;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public RenegociacaoResponse() {
    }

    public RenegociacaoResponse(String id, int quantidade, double valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }


    public String getId() {
        return id;
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

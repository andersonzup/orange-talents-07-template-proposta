package br.com.zup.proposta.cartao.entity;

import br.com.zup.proposta.cartao.response.ParcelaResponse;

import javax.persistence.*;

@Entity
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idParcela;

    private Integer quantidade;

    private double valor;


    @Deprecated
    public Parcela() {
    }

    public Parcela(String idParcela, int quantidade, double valor) {
        this.idParcela = idParcela;
        this.quantidade = quantidade;
        this.valor = valor;
    }
    public Parcela(ParcelaResponse response) {
        this.idParcela = response.getId();
        this.quantidade = response.getQuantidade();
        this.valor = response.getValor();
    }

    public String getIdParcela() {
        return idParcela;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

}

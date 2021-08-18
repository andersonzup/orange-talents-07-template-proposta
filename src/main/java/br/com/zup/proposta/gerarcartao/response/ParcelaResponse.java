package br.com.zup.proposta.gerarcartao.response;

public class ParcelaResponse {
    private String id;
    private int quantidade;
    private double valor;

    @Deprecated
    public ParcelaResponse() {
    }

    public ParcelaResponse(String id, int quantidade, double valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

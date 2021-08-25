package br.com.zup.proposta.carteira;

public class CarteiraResponseApi {
    private String resultado;
    private String id;

    @Deprecated
    public CarteiraResponseApi() {
    }

    public CarteiraResponseApi(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}

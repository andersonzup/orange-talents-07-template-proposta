package br.com.zup.proposta.novaproposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoRequest {

    @NotBlank
    private String lagradouro;

    @NotNull
    private int numero;

    @NotBlank
    private String cep;

    private String complemento;

    @Deprecated
    public EnderecoRequest() {
    }

    public EnderecoRequest(String lagradouro, int numero, String cep, String complemento) {
        this.lagradouro = lagradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
    }
    public Endereco toModel(){
        return new Endereco(this);
    }

    public String getLagradouro() {
        return lagradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }

}

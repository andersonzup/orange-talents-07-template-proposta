package br.com.zup.proposta.novaproposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {

    @NotBlank
    private String lagradouro;

    @NotNull
    private int numero;

    @NotBlank
    private String cep;

    private String complemento;

    @Deprecated
    public Endereco() {
    }

    public Endereco(String lagradouro, int numero, String cep, String complemento) {
        this.lagradouro = lagradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
    }

    public Endereco(EnderecoRequest request) {
        this.lagradouro = request.getLagradouro();
        this.numero = request.getNumero();
        this.cep = request.getCep();
        this.complemento = request.getComplemento();
    }
}

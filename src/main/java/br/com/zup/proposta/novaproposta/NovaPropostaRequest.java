package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.validacao.CPFOrCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class NovaPropostaRequest {

    @CPFOrCNPJ
    @NotBlank
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotNull
    private EnderecoRequest endereco;

    @NotNull
    @PositiveOrZero
    private double salario;

    public NovaPropostaRequest() {
    }

    public NovaPropostaRequest(String documento, String email, String nome, EnderecoRequest endereco, double salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
    public Proposta toModel(){
        return new Proposta(this, this.endereco);
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public double getSalario() {
        return salario;
    }

}

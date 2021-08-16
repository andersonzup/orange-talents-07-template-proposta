package br.com.zup.proposta.novaproposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotNull
    @Embedded
    private Endereco endereco;

    @NotNull
    @PositiveOrZero
    private double salario;

    public Proposta(NovaPropostaRequest request, EnderecoRequest enderecoRequest) {
        this.documento = request.getDocumento();
        this.email = request.getEmail();
        this.nome = request.getNome();
        this.endereco = enderecoRequest.toModel();
        this.salario = request.getSalario();
    }

    public Proposta(String documento, String email, String nome, Endereco endereco, double salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }
}

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

    @Enumerated
    private EstadoProposta estado;

    @Deprecated
    public Proposta() {
    }

    public Proposta(NovaPropostaRequest request, EnderecoRequest enderecoRequest) {
        this.documento = request.getDocumento();
        this.email = request.getEmail();
        this.nome = request.getNome();
        this.endereco = enderecoRequest.toModel();
        this.salario = request.getSalario();
        this.estado = EstadoProposta.AGUARDANDO_APROVACAO;
    }

    public Proposta(String documento, String email, String nome, Endereco endereco, double salario, EstadoProposta estado) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.estado = estado;
    }

    public void setEstado(EstadoProposta estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }
}

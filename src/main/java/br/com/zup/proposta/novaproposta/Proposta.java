package br.com.zup.proposta.novaproposta;

import br.com.zup.proposta.utils.Criptografia;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String documento;

    @NotBlank
    private  String documentohash;

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

    private String cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(NovaPropostaRequest request, EnderecoRequest enderecoRequest) {
        this.documento = criptografar(request.getDocumento());
        this.email = request.getEmail();
        this.nome = request.getNome();
        this.endereco = enderecoRequest.toModel();
        this.salario = request.getSalario();
        this.estado = EstadoProposta.AGUARDANDO_APROVACAO;
        this.documentohash = Criptografia.getSHA512(request.getDocumento());
    }


    private String criptografar(String entrada){
        Criptografia criptografia = new Criptografia();
        return criptografia.encode(entrada);
    }

    public void atualizaEstado(EstadoProposta estado) {
        this.estado = estado;
    }

    public void insereNumeroCartao(String numeroCartao) {
        @Size(min = 19, max = 19)
        String caratao = numeroCartao;
        this.cartao = caratao;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        Criptografia criptografia = new Criptografia();
        return criptografia.decode(documento);
    }

    public String getDocumentohash() {
        return documentohash;
    }

    public String getNome() {
        return nome;
    }

    public EstadoProposta getEstado() {
        return estado;
    }

    public String getCartao() {
        return cartao;
    }
}

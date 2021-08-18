package br.com.zup.proposta.estadoproposta;

import br.com.zup.proposta.novaproposta.EstadoProposta;

public class EstadoPropostaResponse {
    private Long id;
    private String nome;
    private EstadoProposta estadoProposta;

    @Deprecated
    public EstadoPropostaResponse() {
    }

    public EstadoPropostaResponse(Long id, String nome, EstadoProposta estadoProposta) {
        this.id = id;
        this.nome = nome;
        this.estadoProposta = estadoProposta;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }
}

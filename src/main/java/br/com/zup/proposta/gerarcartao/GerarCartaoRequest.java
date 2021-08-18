package br.com.zup.proposta.gerarcartao;

import br.com.zup.proposta.novaproposta.Proposta;

public class GerarCartaoRequest {
    private String documento;
    private String nome;
    private Long idProposta;

    public GerarCartaoRequest() {

    }

    public GerarCartaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}

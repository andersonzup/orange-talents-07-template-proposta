package br.com.zup.proposta.novaproposta.consultarestricao;

import br.com.zup.proposta.novaproposta.Proposta;

public class ConsultaRestricaoRequest {
    private String documento;
    private String nome;
    private Long idProposta;

    public ConsultaRestricaoRequest() {

    }

    public ConsultaRestricaoRequest(Proposta proposta) {
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

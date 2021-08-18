package br.com.zup.proposta.gerarcartao.response;

import java.time.LocalDateTime;

public class VencimentoResponse {
    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public VencimentoResponse() {
    }

    public VencimentoResponse(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}

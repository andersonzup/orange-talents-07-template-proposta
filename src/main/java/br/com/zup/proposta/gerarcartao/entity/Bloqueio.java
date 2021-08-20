package br.com.zup.proposta.gerarcartao.entity;

import br.com.zup.proposta.gerarcartao.response.BloqueioResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idBloqueio;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String idBloqueio, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.idBloqueio = idBloqueio;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }
    public Bloqueio(BloqueioResponse response) {
        this.idBloqueio = response.getId();
        this.bloqueadoEm = response.getBloqueadoEm();
        this.sistemaResponsavel = response.getSistemaResponsavel();
        this.ativo = response.isAtivo();
    }

    public String getId() {
        return idBloqueio;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }
}

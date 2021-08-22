package br.com.zup.proposta.bloqueiocartao;

import br.com.zup.proposta.cartao.entity.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime bloqueadoEm;
    private String sistema;
    private String ip;
    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(LocalDateTime bloqueadoEm, String sistema, String ip, boolean ativo, Cartao cartao) {
        this.bloqueadoEm = bloqueadoEm;
        this.sistema = sistema;
        this.ip = ip;
        this.ativo = ativo;
        this.cartao = cartao;
    }

    public Bloqueio(BloqueioAtivoResponse response, String ip, Cartao cartao) {
        this.bloqueadoEm = response.getBloqueadoEm();
        this.sistema = response.getSistemaResponsavel();
        this.ip = ip;
        this.ativo = response.isAtivo();
        this.cartao = cartao;
    }



    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistema() {
        return sistema;
    }

    public boolean isAtivo() {
        return ativo;
    }
}

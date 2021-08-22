package br.com.zup.proposta.cartao.entity;

import br.com.zup.proposta.cartao.response.CarteiraResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idCarteira;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String idCarteira, String email, LocalDateTime associadaEm, String emissor) {
        this.idCarteira = idCarteira;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira(CarteiraResponse response) {
        this.idCarteira = response.getId();
        this.email = response.getEmail();
        this.associadaEm = response.getAssociadaEm();
        this.emissor = response.getEmissor();
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}

package br.com.zup.proposta.carteira;

import br.com.zup.proposta.cartao.response.CarteiraResponse;
import br.com.zup.proposta.carteira.carteiras.EnumCarteira;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idCarteira;
    private String email;
    private LocalDateTime associadaEm = LocalDateTime.now();

    private EnumCarteira emissor;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String idCarteira, String email, EnumCarteira associacao) {
        this.idCarteira = idCarteira;
        this.email = email;
        this.emissor = associacao;
    }

    public Carteira(CarteiraResponse response) {
        this.idCarteira = response.getId();
        this.email = response.getEmail();
        this.associadaEm = response.getAssociadaEm();
        this.emissor = response.getEmissor();
    }


    public Long getId() {
        return id;
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

    public EnumCarteira getEmissor() {
        return emissor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carteira)) return false;
        Carteira carteira = (Carteira) o;
        return emissor == carteira.emissor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emissor);
    }
}

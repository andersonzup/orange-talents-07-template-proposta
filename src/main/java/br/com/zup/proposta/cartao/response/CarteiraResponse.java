package br.com.zup.proposta.cartao.response;

import br.com.zup.proposta.carteira.carteiras.EnumCarteira;

import java.time.LocalDateTime;

public class CarteiraResponse {
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private EnumCarteira emissor;

    @Deprecated
    public CarteiraResponse() {
    }

    public CarteiraResponse(String id, String email, LocalDateTime associadaEm, EnumCarteira emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
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
}

package br.com.zup.proposta.cartao.entity;


import br.com.zup.proposta.bloqueiocartao.Bloqueio;
import br.com.zup.proposta.cartao.response.CartaoGeradoResponse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numero;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cartao_id")
    private List<Bloqueio> bloqueio;

    @OneToMany
    @JoinColumn(name = "cartao_id")
    private List<Aviso> avisos;

    @OneToMany
    @JoinColumn(name = "cartao")
    private List<Carteira> carteiras;

    private double limite;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn
    private Vencimento vencimento;

    private String idProposta;

    @Deprecated
    public Cartao() {
    }
    @Deprecated
    public Cartao(String numero, LocalDateTime emitidoEm, String titular,
                  List<Bloqueio> bloqueio, List<Aviso> avisos, List<Carteira> carteiras, double limite,
                  Vencimento vencimento, String idProposta) {

        this.numero = numero;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueio = bloqueio;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.limite = limite;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Cartao(CartaoGeradoResponse response) {

        this.numero = response.getId();
        this.emitidoEm = response.getEmitidoEm();
        this.titular = response.getTitular();
        this.carteiras = toCarteirasList(response);
        this.limite = response.getLimite();
        this.vencimento = new Vencimento(response.getVencimento());
        this.idProposta = response.getIdProposta();
    }

    private List<Parcela> toParcelasList(CartaoGeradoResponse response) {
        List<Parcela> parcelas = new ArrayList<>();
        response.getParcelas().forEach(e-> parcelas.add(new Parcela(e)));
        return parcelas;
    }

    private List<Carteira> toCarteirasList(CartaoGeradoResponse response) {
        List<Carteira> carteiras = new ArrayList<>();
        response.getCarteiras().forEach(e-> carteiras.add(new Carteira(e)));
        return carteiras;
    }

    private List<Aviso> toAvisoList(CartaoGeradoResponse response){
        List<Aviso> avisos = new ArrayList<>();
        response.getAvisos().forEach(e-> avisos.add(new Aviso(e)));
        return avisos;
    }

    public String getNumero() {
        return numero;
    }

    public List<Bloqueio> getBloqueio() {
        return bloqueio;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "idCartao='" + numero + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                '}';
    }
}

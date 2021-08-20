package br.com.zup.proposta.gerarcartao.entity;


import br.com.zup.proposta.gerarcartao.response.CartaoGeradoResponse;

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

    @OneToMany
    private List<Bloqueio> bloqueios;

    @OneToMany
    private List<Aviso> avisos;

    @OneToMany
    private List<Carteira> carteiras;

    private double limite;

    @Embedded
    private Vencimento vencimentoResponse;

    private String idProposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, LocalDateTime emitidoEm, String titular,
                  List<Bloqueio> bloqueios, List<Aviso> avisos, List<Carteira> carteiras, double limite,
                  Vencimento vencimentoResponse, String idProposta) {

        this.numero = numero;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.limite = limite;
        this.vencimentoResponse = vencimentoResponse;
        this.idProposta = idProposta;
    }

    public Cartao(CartaoGeradoResponse response) {

        this.numero = response.getId();
        this.emitidoEm = response.getEmitidoEm();
        this.titular = response.getTitular();
        this.bloqueios = toBloqueioList(response);
        this.carteiras = toCarteirasList(response);
        this.limite = response.getLimite();
        this.vencimentoResponse = new Vencimento(response.getVencimento());
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

    private List<Bloqueio> toBloqueioList(CartaoGeradoResponse response){
        List<Bloqueio> bloqueios = new ArrayList<>();
        response.getBloqueios().forEach(e-> bloqueios.add(new Bloqueio(e)));
        return bloqueios;
    }
    private List<Aviso> toAvisoList(CartaoGeradoResponse response){
        List<Aviso> avisos = new ArrayList<>();
        response.getAvisos().forEach(e-> avisos.add(new Aviso(e)));
        return avisos;
    }

    public String getNumero() {
        return numero;
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

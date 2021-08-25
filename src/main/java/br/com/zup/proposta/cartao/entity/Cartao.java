package br.com.zup.proposta.cartao.entity;


import br.com.zup.proposta.avisoviagem.Aviso;
import br.com.zup.proposta.bloqueiocartao.Bloqueio;
import br.com.zup.proposta.cartao.StatusCartao;
import br.com.zup.proposta.cartao.response.CartaoGeradoResponse;
import br.com.zup.proposta.carteira.Carteira;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.zup.proposta.cartao.StatusCartao.*;

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

    private StatusCartao status;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cartao_id")
    private List<Aviso> avisos;

    @OneToMany(cascade = {CascadeType.ALL})
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
        this.limite = response.getLimite();
        this.vencimento = new Vencimento(response.getVencimento());
        this.idProposta = response.getIdProposta();
        this.status = DESBLOQUEADO;
    }

    public void atualizaStatusCartao(){
        if (DESBLOQUEADO.equals(this.status)){
            this.status = BLOQUEADO;
        }else if (BLOQUEADO.equals(this.status)){
            this.status = DESBLOQUEADO;
        }
    }

    private List<Parcela> toParcelasList(CartaoGeradoResponse response) {
        List<Parcela> parcelas = new ArrayList<>();
        response.getParcelas().forEach(e-> parcelas.add(new Parcela(e)));
        return parcelas;
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

    public StatusCartao getStatus() {
        return status;
    }


    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cartao)) return false;
        Cartao cartao = (Cartao) o;
        return status == cartao.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
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

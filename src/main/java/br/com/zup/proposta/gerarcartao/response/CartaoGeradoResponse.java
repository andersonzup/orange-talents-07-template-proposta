package br.com.zup.proposta.gerarcartao.response;

import java.time.LocalDateTime;
import java.util.List;

public class CartaoGeradoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioResponse> bloqueios;
    private List<AvisoResponse> avisos;
    private List<CarteiraResponse> carteiras;
    private List<ParcelaResponse> parcelas;
    private double limite;
    private RenegociacaoResponse renegociacao;
    private VencimentoResponse vencimento;
    private String idProposta;

    @Deprecated
    public CartaoGeradoResponse() {
    }

    public CartaoGeradoResponse(String id, LocalDateTime emitidoEm, String titular, List<BloqueioResponse> bloqueios, List<AvisoResponse> avisos, List<CarteiraResponse> carteiras,
                                List<ParcelaResponse> parcelas, double limite, RenegociacaoResponse renegociacao, VencimentoResponse vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<BloqueioResponse> getBloqueios() {
        return bloqueios;
    }

    public List<AvisoResponse> getAvisos() {
        return avisos;
    }

    public List<CarteiraResponse> getCarteiras() {
        return carteiras;
    }

    public List<ParcelaResponse> getParcelas() {
        return parcelas;
    }

    public double getLimite() {
        return limite;
    }

    public RenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "CartaoGeradoResponse{" +
                "id='" + id + '\'' +
                "\n, emitidoEm=" + emitidoEm +
                "\n, titular='" + titular + '\'' +
                "\n, bloqueios=" + bloqueios +
                "\n, avisos=" + avisos +
                "\n, carteiras=" + carteiras +
                "\n, parcelas=" + parcelas +
                "\n, limite=" + limite +
                "\n, renegociacao=" + renegociacao +
                "\n, vencimentoResponse=" + vencimento +
                "\n, idProposta='" + idProposta + '\'' +
                '}';
    }
}

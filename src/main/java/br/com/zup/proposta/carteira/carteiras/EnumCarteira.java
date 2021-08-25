package br.com.zup.proposta.carteira.carteiras;

import br.com.zup.proposta.carteira.carteiras.ContratoCarteira;
import br.com.zup.proposta.carteira.carteiras.ImplementacaoPaypal;
import br.com.zup.proposta.carteira.carteiras.ImplementacaoSansungPay;

public enum EnumCarteira {

    PAYPAL(new ImplementacaoPaypal())
    , SANSUNGPAY(new ImplementacaoSansungPay());


    private final ContratoCarteira contratoCarteira;

    EnumCarteira(ContratoCarteira contratoCarteira) {
        this.contratoCarteira = contratoCarteira;
    }

    public ContratoCarteira getContratoCarteira() {
        return contratoCarteira;
    }
}

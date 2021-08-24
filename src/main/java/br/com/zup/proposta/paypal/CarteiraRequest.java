package br.com.zup.proposta.paypal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.zup.proposta.paypal.EnumCarteira.PAYPAL;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private EnumCarteira carteira;

    @Deprecated
    public CarteiraRequest() {
    }

    public CarteiraRequest(String email, EnumCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public EnumCarteira getCarteira() {
        return carteira;
    }

    @Override
    public String toString() {
        return "CarteiraRequestPaypal{" +
                "email='" + email + '\'' +
                ", carteira=" + carteira +
                '}';
    }
}

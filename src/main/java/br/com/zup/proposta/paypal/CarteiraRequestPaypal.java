package br.com.zup.proposta.paypal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.zup.proposta.paypal.EnumCarteira.*;

public class CarteiraRequestPaypal {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private EnumCarteira carteira = PAYPAL;

    @Deprecated
    public CarteiraRequestPaypal() {
    }

    public CarteiraRequestPaypal(String email, EnumCarteira carteira) {
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

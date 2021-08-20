package br.com.zup.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CartaoNotFoundException extends RuntimeException {
    public CartaoNotFoundException(String cartao) {
        super("Cartão com número "+cartao+" não encontrado");
    }
}

package br.com.zup.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PropostaNotFoundException extends RuntimeException {
    public PropostaNotFoundException(Long id) {
        super("Proposta com id "+id+" não encontrado");
    }
}

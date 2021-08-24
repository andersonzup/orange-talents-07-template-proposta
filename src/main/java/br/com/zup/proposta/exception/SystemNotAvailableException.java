package br.com.zup.proposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SystemNotAvailableException extends RuntimeException {
    public SystemNotAvailableException(String message) {
        super(message);
    }
}

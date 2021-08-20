package br.com.zup.proposta.exception;

import org.springframework.http.HttpStatus;

public class IllegalBase64Exception extends RuntimeException{

    private final HttpStatus httpStatus;

    private final String reason;

    public IllegalBase64Exception(HttpStatus httpStatus, String reason) {
        super(reason);
        this.httpStatus = httpStatus;
        this.reason = reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getReason() {
        return reason;
    }
}

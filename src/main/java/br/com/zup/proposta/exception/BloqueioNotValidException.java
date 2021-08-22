package br.com.zup.proposta.exception;

public class BloqueioNotValidException extends RuntimeException {
    public BloqueioNotValidException(String message) {
        super(message);
    }
}

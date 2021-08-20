package br.com.zup.proposta.validacao;

import br.com.zup.proposta.exception.IllegalBase64Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            new String(java.util.Base64.getDecoder().decode(value));
            return true;

        } catch (IllegalArgumentException e) {
            throw  new IllegalBase64Exception(HttpStatus.BAD_REQUEST, constraintValidatorContext.getDefaultConstraintMessageTemplate());
        }

    }
}

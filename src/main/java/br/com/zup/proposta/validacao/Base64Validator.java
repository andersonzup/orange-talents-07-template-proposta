package br.com.zup.proposta.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<Base64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String decoded = new String(java.util.Base64.getDecoder().decode(value));
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }

    }
}

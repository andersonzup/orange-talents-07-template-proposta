package br.com.zup.proposta.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValueValidator.class)
public @interface UniqueValue {
    String message() default "{Duplicidade no sistema}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String value() default "";
    String fieldName();
    Class<?> domainClass();

}

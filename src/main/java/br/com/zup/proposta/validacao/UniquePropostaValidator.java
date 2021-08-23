package br.com.zup.proposta.validacao;

import br.com.zup.proposta.exception.PropostaNotValidException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
public class UniquePropostaValidator implements ConstraintValidator<UniqueProposta, Object> {

    private String atributos;
    private Class<?> dClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueProposta parametros) {
        atributos = parametros.fieldName();
        dClass = parametros.domainClass();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("select c from "+dClass.getName()+" c where c."+atributos+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um "+dClass+" com o atributo "+atributos+" = "+value);
       if (!list.isEmpty()){
           throw new PropostaNotValidException(context.getDefaultConstraintMessageTemplate());
       }

        return list.isEmpty();


    }
}
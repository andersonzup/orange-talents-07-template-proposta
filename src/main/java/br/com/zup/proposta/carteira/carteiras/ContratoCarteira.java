package br.com.zup.proposta.carteira.carteiras;

import br.com.zup.proposta.carteira.Carteira;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface ContratoCarteira {
    ResponseEntity<Object> implementacaoCarteiras(UriComponentsBuilder componentsBuilder, Carteira carteira);
}

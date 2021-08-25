package br.com.zup.proposta.carteira.carteiras;

import br.com.zup.proposta.carteira.Carteira;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ImplementacaoPaypal implements ContratoCarteira{
    @Override
    public ResponseEntity<Object> implementacaoCarteiras(UriComponentsBuilder componentsBuilder, Carteira carteira) {
        URI uri = componentsBuilder.path("/api/v1/paypal/{id}").buildAndExpand(carteira.getIdCarteira()).toUri();
        return ResponseEntity.created(uri).build();
    }
}

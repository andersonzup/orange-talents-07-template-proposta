package br.com.zup.proposta.carteira.carteiras;

import br.com.zup.proposta.carteira.Carteira;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ImplementacaoSansungPay implements ContratoCarteira{
    @Override
    public ResponseEntity<Object> implementacaoCarteiras(UriComponentsBuilder componentsBuilder, Carteira carteira, HttpStatus status) {



        URI uri = componentsBuilder.path("/api/v1/sansungpay/{id}").buildAndExpand(carteira.getIdCarteira()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri.toString());
        return new ResponseEntity<>(headers, status);

    }
}

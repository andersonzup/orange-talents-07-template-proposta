package br.com.zup.proposta.paypal;

import br.com.zup.proposta.cartao.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}

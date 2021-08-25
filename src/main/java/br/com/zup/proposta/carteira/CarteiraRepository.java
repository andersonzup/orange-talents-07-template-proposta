package br.com.zup.proposta.carteira;

import br.com.zup.proposta.carteira.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}

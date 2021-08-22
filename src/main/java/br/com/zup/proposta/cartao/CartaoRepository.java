package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Cartao findByNumero(String id);

    boolean existsByNumero(String id);
}

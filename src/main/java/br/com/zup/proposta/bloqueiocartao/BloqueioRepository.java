package br.com.zup.proposta.bloqueiocartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
        boolean existsByCartaoAndAtivoTrue(Long id);
}

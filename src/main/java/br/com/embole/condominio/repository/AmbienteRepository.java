package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Ambiente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
    Page<Ambiente> findByCondominioNomeContaining(String nomeCondominio, Pageable paginacao);
}

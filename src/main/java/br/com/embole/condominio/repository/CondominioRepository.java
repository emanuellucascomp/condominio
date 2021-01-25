package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Condominio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    Page<Condominio> findByNome(String nome, Pageable paginacao);
}

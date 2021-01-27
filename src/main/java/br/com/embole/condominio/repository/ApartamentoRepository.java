package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Apartamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
    Page<Apartamento> findByCondominioNomeContaining(String nomeCondominio, Pageable paginacao);
}

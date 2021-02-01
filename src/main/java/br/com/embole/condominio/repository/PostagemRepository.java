package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    Page<Postagem> findByTitulo(String titulo, Pageable paginacao);
}

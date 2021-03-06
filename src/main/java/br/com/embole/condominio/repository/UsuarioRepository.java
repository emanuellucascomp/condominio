package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String username);

    Page<Usuario> findByNome(String nome, Pageable paginacao);
}

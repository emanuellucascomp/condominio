package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}

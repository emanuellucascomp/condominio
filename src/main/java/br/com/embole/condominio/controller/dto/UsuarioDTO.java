package br.com.embole.condominio.controller.dto;

import br.com.embole.condominio.model.Usuario;
import org.springframework.data.domain.Page;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public static Page<UsuarioDTO> converter(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDTO::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

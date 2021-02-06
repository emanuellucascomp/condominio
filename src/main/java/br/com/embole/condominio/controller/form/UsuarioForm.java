package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Usuario;

import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converter() {
        return new Usuario(nome, email, senha);
    }
}

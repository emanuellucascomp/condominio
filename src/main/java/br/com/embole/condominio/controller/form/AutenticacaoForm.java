package br.com.embole.condominio.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class AutenticacaoForm {

    @NotBlank
    private String login;
    @NotBlank
    private String senha;

    public AutenticacaoForm(@NotBlank String login, @NotBlank String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toDado() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}

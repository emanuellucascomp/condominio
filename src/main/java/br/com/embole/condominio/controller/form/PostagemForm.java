package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Postagem;
import br.com.embole.condominio.model.Usuario;
import br.com.embole.condominio.repository.PostagemRepository;
import br.com.embole.condominio.repository.UsuarioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PostagemForm {

    @NotBlank
    private String titulo;
    @NotBlank
    private String corpo;
    @NotNull
    private Long usuarioId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Postagem converter(UsuarioRepository usuarioRepository){
        Usuario usuario = usuarioRepository.getOne(usuarioId);
        return new Postagem(titulo, corpo, usuario);
    }

    public Postagem atualizar(Long id, PostagemRepository postagemRepository, UsuarioRepository usuarioRepository){
        Usuario usuario = usuarioRepository.getOne(usuarioId);
        Postagem postagem = postagemRepository.getOne(id);
        postagem.setTitulo(titulo);
        postagem.setCorpo(corpo);
        postagem.setUsuario(usuario);

        return postagem;
    }

    //TODO parte de moderação
}

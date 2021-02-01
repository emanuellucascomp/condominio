package br.com.embole.condominio.controller.dto;

import br.com.embole.condominio.model.Postagem;
import org.springframework.data.domain.Page;

public class PostagemDTO {

    private Long id;
    private String titulo;
    private String corpo;
    private Long usuarioId;

    public PostagemDTO(Postagem postagem){
        this.id = postagem.getId();
        this.titulo = postagem.getTitulo();
        this.corpo = postagem.getCorpo();
        this.usuarioId = postagem.getUsuario().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static Page<PostagemDTO> converter(Page<Postagem> postagens){
        return postagens.map(PostagemDTO::new);
    }
}

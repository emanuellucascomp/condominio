package br.com.embole.condominio.model;

import javax.persistence.*;

@Entity
public class Apartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bloco;
    private String numero;
    private String quantidadeMoradores;
    @ManyToOne
    private Condominio condominio;
    @OneToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getQuantidadeMoradores() {
        return quantidadeMoradores;
    }

    public void setQuantidadeMoradores(String quantidadeMoradores) {
        this.quantidadeMoradores = quantidadeMoradores;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

package br.com.embole.condominio.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    @OneToMany(mappedBy = "condominio")
    private List<Ambiente> ambientes = new ArrayList<>();
    @OneToMany(mappedBy = "condominio")
    private List<Apartamento> apartamentos = new ArrayList<>();

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    public List<Apartamento> getApartamentos() {
        return apartamentos;
    }

    public void setApartamentos(List<Apartamento> apartamentos) {
        this.apartamentos = apartamentos;
    }
}

package br.com.embole.condominio.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ambiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer ocupacaoMaxima;
    @ManyToOne
    private Condominio condominio;
    @OneToMany(mappedBy = "ambiente")
    private List<Equipamento> equipamentos = new ArrayList<>();

    public Ambiente(){

    }

    public Ambiente(String nome, Integer ocupacaoMaxima, Condominio condominio){
        this.nome = nome;
        this.ocupacaoMaxima = ocupacaoMaxima;
        this.condominio = condominio;
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

    public Integer getOcupacaoMaxima() {
        return ocupacaoMaxima;
    }

    public void setOcupacaoMaxima(Integer ocupacaoMaxima) {
        this.ocupacaoMaxima = ocupacaoMaxima;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
}

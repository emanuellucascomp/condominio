package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Ambiente;
import br.com.embole.condominio.model.Condominio;
import br.com.embole.condominio.repository.AmbienteRepository;
import br.com.embole.condominio.repository.CondominioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AmbienteForm {

    @NotBlank
    private String nome;
    @NotNull
    private Integer ocupacaoMaxima;
    @NotNull
    private Long condominioId;

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

    public Long getCondominioId() {
        return condominioId;
    }

    public void setCondominioId(Long condominioId) {
        this.condominioId = condominioId;
    }

    public Ambiente converter(CondominioRepository condominioRepository){
        Condominio condominio = condominioRepository.getOne(condominioId);
        return new Ambiente(nome, ocupacaoMaxima, condominio);
    }

    public Ambiente atualizar(Long id, AmbienteRepository ambienteRepository, CondominioRepository condominioRepository){
        Ambiente ambiente = ambienteRepository.getOne(id);
        Condominio condominio = condominioRepository.getOne(condominioId);
        ambiente.setNome(this.nome);
        ambiente.setOcupacaoMaxima(this.ocupacaoMaxima);
        ambiente.setCondominio(condominio);

        return ambiente;
    }
}

package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Condominio;
import br.com.embole.condominio.repository.CondominioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CondominioForm {

    @NotBlank @NotNull @Size(min = 5, max = 200)
    private String nome;
    @NotBlank @NotNull @Size(min = 5, max = 200)
    private String endereco;

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

    public Condominio converter(){
        return new Condominio(nome, endereco);
    }

    public Condominio atualizar(Long id, CondominioRepository condominioRepository) {
        Condominio condominio = condominioRepository.getOne(id);
        condominio.setNome(this.nome);
        condominio.setEndereco(this.endereco);

        return condominio;
    }
}

package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Apartamento;
import br.com.embole.condominio.repository.ApartamentoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ApartamentoForm {

    @NotBlank @NotNull @Size(min = 5, max = 200)
    private String bloco;
    @NotBlank @NotNull @Size(min = 5, max = 200)
    private String numero;

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

    public Apartamento converter(){
        return new Apartamento(bloco, numero);
    }

    public Apartamento atualizar(Long id, ApartamentoRepository apartamentoRepository){
        Apartamento apartamento = apartamentoRepository.getOne(id);
        apartamento.setBloco(this.bloco);
        apartamento.setNumero(this.numero);

        return apartamento;
    }
}

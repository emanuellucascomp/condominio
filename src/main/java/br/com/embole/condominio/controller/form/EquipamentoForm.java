package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Ambiente;
import br.com.embole.condominio.model.Equipamento;
import br.com.embole.condominio.repository.AmbienteRepository;
import br.com.embole.condominio.repository.EquipamentoRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EquipamentoForm {

    @NotBlank
    private String nome;
    @NotNull
    private Long ambienteId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Long ambienteId) {
        this.ambienteId = ambienteId;
    }

    public Equipamento converter(AmbienteRepository ambienteRepository){
        Ambiente ambiente = ambienteRepository.getOne(ambienteId);
        return new Equipamento(nome, ambiente);
    }

    public Equipamento atualizar(Long id, EquipamentoRepository equipamentoRepository, AmbienteRepository ambienteRepository){
        Ambiente ambiente = ambienteRepository.getOne(ambienteId);
        Equipamento equipamento = equipamentoRepository.getOne(id);
        equipamento.setNome(nome);
        equipamento.setAmbiente(ambiente);

        return equipamento;
    }
}

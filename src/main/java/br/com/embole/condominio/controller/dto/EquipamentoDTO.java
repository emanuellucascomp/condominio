package br.com.embole.condominio.controller.dto;

import br.com.embole.condominio.model.Equipamento;
import org.springframework.data.domain.Page;

public class EquipamentoDTO {

    private Long id;
    private String nome;
    private Long ambienteId;

    public EquipamentoDTO(Equipamento equipamento){
        this.id = equipamento.getId();
        this.nome = equipamento.getNome();
        this.ambienteId = equipamento.getAmbiente().getId();
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

    public Long getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Long ambienteId) {
        this.ambienteId = ambienteId;
    }

    public static Page<EquipamentoDTO> converter(Page<Equipamento> equipamentos){
        return equipamentos.map(EquipamentoDTO::new);
    }
}

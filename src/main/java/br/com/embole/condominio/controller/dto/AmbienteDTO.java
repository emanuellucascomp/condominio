package br.com.embole.condominio.controller.dto;

import br.com.embole.condominio.model.Ambiente;
import br.com.embole.condominio.model.Equipamento;
import org.springframework.data.domain.Page;

import java.util.List;

public class AmbienteDTO {

    private Long id;
    private String nome;
    private Long condominioId;

    public AmbienteDTO(Ambiente ambiente){
        this.id = ambiente.getId();
        this.nome = ambiente.getNome();
        this.condominioId = ambiente.getCondominio().getId();
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

    public Long getCondominioId() {
        return condominioId;
    }

    public void setCondominioId(Long condominioId) {
        this.condominioId = condominioId;
    }

    public static Page<AmbienteDTO> converter(Page<Ambiente> ambientes){
        return ambientes.map(AmbienteDTO::new);
    }

}

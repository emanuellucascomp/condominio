package br.com.embole.condominio.controller.dto;


import br.com.embole.condominio.model.Apartamento;
import org.springframework.data.domain.Page;

public class ApartamentoDTO {

    private Long id;
    private String bloco;
    private String numero;

    public ApartamentoDTO(Apartamento apartamento){
        this.id = apartamento.getId();
        this.bloco = apartamento.getBloco();
        this.numero = apartamento.getNumero();
    }

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

    public static Page<ApartamentoDTO> converter(Page<Apartamento> apartamentos){
        return apartamentos.map(ApartamentoDTO::new);
    }
}

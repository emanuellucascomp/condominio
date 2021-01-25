package br.com.embole.condominio.controller.dto;

import br.com.embole.condominio.model.Condominio;
import org.springframework.data.domain.Page;

public class CondominioDTO {

    private Long id;
    private String nome;
    private String endereco;

    public CondominioDTO(Condominio condominio){
        this.id = condominio.getId();
        this.nome = condominio.getNome();
        this.endereco = condominio.getEndereco();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public static Page<CondominioDTO> converter(Page<Condominio> condominios){
        return condominios.map(CondominioDTO::new);
    }

}

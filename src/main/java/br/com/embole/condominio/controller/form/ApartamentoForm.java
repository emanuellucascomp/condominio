package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Apartamento;
import br.com.embole.condominio.model.Condominio;
import br.com.embole.condominio.model.Usuario;
import br.com.embole.condominio.repository.ApartamentoRepository;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ApartamentoForm {

    @NotBlank @NotNull
    private String bloco;
    @NotBlank @NotNull
    private String numero;
    @NotNull
    private Long condominioId;
    @NotNull
    private Long usuarioId;

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

    public Long getCondominioId() {
        return condominioId;
    }

    public void setCondominioId(Long condominioId) {
        this.condominioId = condominioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Apartamento converter(EntityManager manager){
        Condominio condominio = manager.find(Condominio.class, condominioId);
        Usuario usuario = manager.find(Usuario.class, usuarioId);
        return new Apartamento(bloco, numero, condominio, usuario);
    }

    public Apartamento atualizar(Long id, ApartamentoRepository apartamentoRepository){
        Apartamento apartamento = apartamentoRepository.getOne(id);
        apartamento.setBloco(this.bloco);
        apartamento.setNumero(this.numero);

        return apartamento;
    }
}

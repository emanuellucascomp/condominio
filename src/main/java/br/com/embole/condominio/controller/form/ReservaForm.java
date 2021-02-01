package br.com.embole.condominio.controller.form;

import br.com.embole.condominio.model.Ambiente;
import br.com.embole.condominio.model.Condominio;
import br.com.embole.condominio.model.Reserva;
import br.com.embole.condominio.model.Usuario;
import br.com.embole.condominio.repository.AmbienteRepository;
import br.com.embole.condominio.repository.CondominioRepository;
import br.com.embole.condominio.repository.ReservaRepository;
import br.com.embole.condominio.repository.UsuarioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReservaForm {

    @NotNull
    private Long condominioId;
    @NotNull
    private Long ambienteId;
    @NotNull
    private Long usuarioId;
    @NotNull
    private LocalDateTime horaInicio;
    @NotNull
    private LocalDateTime horaFim;

    public Long getCondominioId() {
        return condominioId;
    }

    public void setCondominioId(Long condominioId) {
        this.condominioId = condominioId;
    }

    public Long getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Long ambienteId) {
        this.ambienteId = ambienteId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalDateTime horaFim) {
        this.horaFim = horaFim;
    }

    public Reserva converter(CondominioRepository condominioRepository, AmbienteRepository ambienteRepository, UsuarioRepository usuarioRepository, LocalDateTime horaInicio, LocalDateTime horaFim){
        Condominio condomino = condominioRepository.getOne(condominioId);
        Ambiente ambiente = ambienteRepository.getOne(ambienteId);
        Usuario usuario = usuarioRepository.getOne(usuarioId);
        return new Reserva(condomino, ambiente, usuario, horaInicio, horaFim);
    }

    public Reserva atualizar(Long id, ReservaRepository reservaRepository, CondominioRepository condominioRepository, AmbienteRepository ambienteRepository, UsuarioRepository usuarioRepository){
        Condominio condomino = condominioRepository.getOne(condominioId);
        Ambiente ambiente = ambienteRepository.getOne(ambienteId);
        Usuario usuario = usuarioRepository.getOne(usuarioId);
        Reserva reserva = reservaRepository.getOne(id);

        reserva.setCondominio(condomino);
        reserva.setAmbiente(ambiente);
        reserva.setUsuario(usuario);
        reserva.setHoraInicio(horaInicio);
        reserva.setHoraFim(horaFim);

        return reserva;
    }
}

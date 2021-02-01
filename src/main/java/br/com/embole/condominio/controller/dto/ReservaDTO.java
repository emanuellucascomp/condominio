package br.com.embole.condominio.controller.dto;

import br.com.embole.condominio.model.Reserva;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class ReservaDTO {

    private Long id;
    private Long condominioId;
    private Long ambienteId;
    private Long usuarioId;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;

    public ReservaDTO(Reserva reserva){
        this.id = reserva.getId();
        this.condominioId = reserva.getCondominio().getId();
        this.ambienteId = reserva.getAmbiente().getId();
        this.usuarioId = reserva.getUsuario().getId();
        this.horaInicio = reserva.getHoraInicio();
        this.horaFim = reserva.getHoraFim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static Page<ReservaDTO> converter(Page<Reserva> reservas){
        return reservas.map(ReservaDTO::new);
    }
}

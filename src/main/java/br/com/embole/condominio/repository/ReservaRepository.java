package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Page<Reserva> findByAmbienteNomeContaining(String nomeAmbiente, Pageable paginacao);
}

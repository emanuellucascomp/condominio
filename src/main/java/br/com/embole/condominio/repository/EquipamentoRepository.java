package br.com.embole.condominio.repository;

import br.com.embole.condominio.model.Equipamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    Page<Equipamento> findByAmbienteNomeContaining(String nomeAmbiente, Pageable paginacao);
}

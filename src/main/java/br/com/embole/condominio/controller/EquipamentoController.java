package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.EquipamentoDTO;
import br.com.embole.condominio.controller.form.EquipamentoForm;
import br.com.embole.condominio.model.Equipamento;
import br.com.embole.condominio.repository.AmbienteRepository;
import br.com.embole.condominio.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/equipamento")
public class EquipamentoController {

    @Autowired
    private AmbienteRepository ambienteRepository;
    @Autowired
    private EquipamentoRepository equipamentoRepository;
    
    @GetMapping
    public Page<EquipamentoDTO> listar(@RequestParam(required = false) String nomeAmbiente,
                                       @PageableDefault(sort = "id",
                                               direction = Sort.Direction.ASC,
                                               page = 0,
                                               size = 10) Pageable paginacao){
        if(nomeAmbiente != null){
            Page<Equipamento> equipamentos = equipamentoRepository.findByAmbienteNomeContaining(nomeAmbiente, paginacao);
            return EquipamentoDTO.converter(equipamentos);
        }
        Page<Equipamento> equipamentos = equipamentoRepository.findAll(paginacao);
        return EquipamentoDTO.converter(equipamentos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EquipamentoDTO> cadastrar(@RequestBody @Valid EquipamentoForm equipamentoForm, UriComponentsBuilder uriBuilder){
        Equipamento equipamento = equipamentoForm.converter(ambienteRepository);
        equipamentoRepository.save(equipamento);

        URI uri = uriBuilder.path("/api/v1/equipamento/{id}").buildAndExpand(equipamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new EquipamentoDTO(equipamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> detalhar(@PathVariable Long id){
        Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
        if(equipamento.isPresent()){
            return ResponseEntity.ok(new EquipamentoDTO(equipamento.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EquipamentoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid EquipamentoForm equipamentoForm){
        Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
        if(equipamento.isPresent()){
            Equipamento equipamentoAtualizado = equipamentoForm.atualizar(id, equipamentoRepository, ambienteRepository);
            return ResponseEntity.ok(new EquipamentoDTO(equipamentoAtualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Equipamento> equipamento = equipamentoRepository.findById(id);
        if(equipamento.isPresent()){
            equipamentoRepository.deleteById(equipamento.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}

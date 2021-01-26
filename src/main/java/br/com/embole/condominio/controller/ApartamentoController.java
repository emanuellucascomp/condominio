package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.ApartamentoDTO;
import br.com.embole.condominio.controller.form.ApartamentoForm;
import br.com.embole.condominio.model.Apartamento;
import br.com.embole.condominio.repository.ApartamentoRepository;
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
@RequestMapping("/api/v1/apartamento")
public class ApartamentoController {

    @Autowired
    private ApartamentoRepository apartamentoRepository;

    @GetMapping
    public Page<ApartamentoDTO> lista(@RequestParam(required = false) String nomeCondominio,
                                      @PageableDefault(sort = "id",
                                              direction = Sort.Direction.ASC,
                                              page = 0,
                                              size = 10) Pageable paginacao){
        if(nomeCondominio != null){
            Page<Apartamento> apartamentos = apartamentoRepository.findByCondominioNome(nomeCondominio, paginacao);
            return ApartamentoDTO.converter(apartamentos);
        }
        Page<Apartamento> apartamentos = apartamentoRepository.findAll(paginacao);
        return ApartamentoDTO.converter(apartamentos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ApartamentoDTO> cadastrar(@RequestBody @Valid ApartamentoForm apartamentoForm, UriComponentsBuilder uriBuilder){
        Apartamento apartamento = apartamentoForm.converter();
        apartamentoRepository.save(apartamento);

        URI uri = uriBuilder.path("/api/v1/apartamento/{id}").buildAndExpand(apartamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApartamentoDTO(apartamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartamentoDTO> detalhar(@PathVariable Long id){
        Optional<Apartamento> apartamento = apartamentoRepository.findById(id);
        if(apartamento.isPresent()){
            return ResponseEntity.ok(new ApartamentoDTO(apartamento.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ApartamentoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ApartamentoForm apartamentoForm){
        Optional<Apartamento> apartamento = apartamentoRepository.findById(id);
        if(apartamento.isPresent()){
            Apartamento apartamentoAtualizado = apartamentoForm.atualizar(apartamento.get().getId(), apartamentoRepository);
            return ResponseEntity.ok(new ApartamentoDTO(apartamentoAtualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Apartamento> apartamento = apartamentoRepository.findById(id);
        if(apartamento.isPresent()){
            apartamentoRepository.deleteById(apartamento.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

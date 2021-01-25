package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.CondominioDTO;
import br.com.embole.condominio.controller.form.CondominioForm;
import br.com.embole.condominio.model.Condominio;
import br.com.embole.condominio.repository.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/condominio")
public class CondominioController {

    @Autowired
    private CondominioRepository condominioRepository;

    @GetMapping
    public Page<CondominioDTO> lista(@RequestParam(required = false) String nome,
                                     @PageableDefault(sort = "id",
                                 direction = Direction.ASC,
                                 page = 0,
                                 size = 10) Pageable paginacao){
        if(nome != null){
            Page<Condominio> condominios = condominioRepository.findByNome(nome, paginacao);
            return CondominioDTO.converter(condominios);
        }
        Page<Condominio> condominios = condominioRepository.findAll(paginacao);
        return CondominioDTO.converter(condominios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CondominioDTO> cadastrar(@RequestBody @Valid CondominioForm condominioForm, UriComponentsBuilder uriBuilder){
        Condominio condominio = condominioForm.converter();
        condominioRepository.save(condominio);

        URI uri = uriBuilder.path("/api/v1/condominio/{id}").buildAndExpand(condominio.getId()).toUri();
        return ResponseEntity.created(uri).body(new CondominioDTO(condominio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondominioDTO> detalhar(@PathVariable Long id){
        Optional<Condominio> condominio = condominioRepository.findById(id);
        if(condominio.isPresent()){
            return ResponseEntity.ok(new CondominioDTO(condominio.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CondominioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CondominioForm condominioForm){
        Optional<Condominio> condominio = condominioRepository.findById(id);
        if(condominio.isPresent()){
            Condominio condominioAtualizado = condominioForm.atualizar(condominio.get().getId(), condominioRepository);
            return ResponseEntity.ok(new CondominioDTO(condominioAtualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Condominio> condominio = condominioRepository.findById(id);
        if(condominio.isPresent()){
            condominioRepository.deleteById(condominio.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

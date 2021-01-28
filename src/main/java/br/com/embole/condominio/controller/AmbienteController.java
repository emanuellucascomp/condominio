package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.AmbienteDTO;
import br.com.embole.condominio.controller.form.AmbienteForm;
import br.com.embole.condominio.model.Ambiente;
import br.com.embole.condominio.repository.AmbienteRepository;
import br.com.embole.condominio.repository.CondominioRepository;
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

@RestController
@RequestMapping("/api/v1/ambiente")
public class AmbienteController {

    @Autowired
    private AmbienteRepository ambienteRepository;
    @Autowired
    private CondominioRepository condominioRepository;

    @GetMapping
    public Page<AmbienteDTO> lista(@RequestParam(required = false) String nomeCondominio,
                                   @PageableDefault(sort = "id",
                                           direction = Sort.Direction.ASC,
                                           page = 0,
                                           size = 10) Pageable paginacao){
        if(nomeCondominio != null){
            Page<Ambiente> ambientes = ambienteRepository.findByCondominioNomeContaining(nomeCondominio, paginacao);
            return AmbienteDTO.converter(ambientes);
        }
        Page<Ambiente> ambientes = ambienteRepository.findAll(paginacao);
        return AmbienteDTO.converter(ambientes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AmbienteDTO> cadastrar(@RequestBody @Valid AmbienteForm ambienteForm, UriComponentsBuilder uriBuilder){
        Ambiente ambiente = ambienteForm.converter(condominioRepository);
        ambienteRepository.save(ambiente);

        URI uri = uriBuilder.path("/api/v1/ambiente/{id}").buildAndExpand(ambiente.getId()).toUri();
        return ResponseEntity.created(uri).body(new AmbienteDTO(ambiente));
    }
}

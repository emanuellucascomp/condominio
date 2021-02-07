package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.CondominioDTO;
import br.com.embole.condominio.controller.dto.ReservaDTO;
import br.com.embole.condominio.controller.form.CondominioForm;
import br.com.embole.condominio.controller.form.ReservaForm;
import br.com.embole.condominio.model.Condominio;
import br.com.embole.condominio.model.Reserva;
import br.com.embole.condominio.repository.AmbienteRepository;
import br.com.embole.condominio.repository.CondominioRepository;
import br.com.embole.condominio.repository.ReservaRepository;
import br.com.embole.condominio.repository.UsuarioRepository;
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
@RequestMapping("/api/v1/reserva")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private CondominioRepository condominioRepository;
    @Autowired
    private AmbienteRepository ambienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<ReservaDTO> listar(@RequestParam(required = false) String nomeAmbiente,
                                   @PageableDefault(sort = "id",
                                           direction = Sort.Direction.ASC,
                                           page = 0,
                                           size = 10) Pageable paginacao){
        if(nomeAmbiente != null){
            Page<Reserva> reservas = reservaRepository.findByAmbienteNomeContaining(nomeAmbiente, paginacao);
            return ReservaDTO.converter(reservas);
        }
        Page<Reserva> reservas = reservaRepository.findAll(paginacao);
        return ReservaDTO.converter(reservas);
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> cadastrar(@RequestBody @Valid ReservaForm reservaForm, UriComponentsBuilder uriBuilder){
        Reserva reserva = reservaForm.converter(condominioRepository, ambienteRepository, usuarioRepository);
        reservaRepository.save(reserva);

        URI uri = uriBuilder.path("/api/v1/reserva/{id}").buildAndExpand(reserva.getId()).toUri();
        return ResponseEntity.created(uri).body(new ReservaDTO(reserva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> detalhar(@PathVariable Long id){
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if(reserva.isPresent()){
            return ResponseEntity.ok(new ReservaDTO(reserva.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReservaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ReservaForm reservaForm){
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if(reserva.isPresent()){
            Reserva reservaAtualizada = reservaForm.atualizar(reserva.get().getId(), reservaRepository, condominioRepository, ambienteRepository, usuarioRepository);
            return ResponseEntity.ok(new ReservaDTO(reservaAtualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if(reserva.isPresent()){
            reservaRepository.deleteById(reserva.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

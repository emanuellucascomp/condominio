package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.ReservaDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

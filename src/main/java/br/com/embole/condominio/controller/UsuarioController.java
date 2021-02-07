package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.CondominioDTO;
import br.com.embole.condominio.controller.dto.UsuarioDTO;
import br.com.embole.condominio.controller.form.UsuarioForm;
import br.com.embole.condominio.model.Condominio;
import br.com.embole.condominio.model.Usuario;
import br.com.embole.condominio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<UsuarioDTO> lista(@RequestParam(required = false) String nome,
                                     @PageableDefault(sort = "id",
                                             direction = Sort.Direction.ASC,
                                             page = 0,
                                             size = 10) Pageable paginacao){
        if(nome != null){
            Page<Usuario> usuarios = usuarioRepository.findByNome(nome, paginacao);
            return UsuarioDTO.converter(usuarios);
        }
        Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
        return UsuarioDTO.converter(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioForm.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/api/v1/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
}

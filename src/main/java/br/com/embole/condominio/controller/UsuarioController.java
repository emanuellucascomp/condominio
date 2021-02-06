package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.UsuarioDTO;
import br.com.embole.condominio.controller.form.UsuarioForm;
import br.com.embole.condominio.model.Usuario;
import br.com.embole.condominio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioForm.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/api/v1/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }
}

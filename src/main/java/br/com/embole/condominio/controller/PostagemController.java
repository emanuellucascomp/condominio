package br.com.embole.condominio.controller;

import br.com.embole.condominio.controller.dto.PostagemDTO;
import br.com.embole.condominio.controller.form.PostagemForm;
import br.com.embole.condominio.model.Postagem;
import br.com.embole.condominio.repository.PostagemRepository;
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
@RequestMapping("/api/v1/postagem")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<PostagemDTO> listar(@RequestParam(required = false) String titulo,
                                              @PageableDefault(sort = "id",
                                                      direction = Sort.Direction.ASC,
                                                      page = 0,
                                                      size = 10) Pageable paginacao){
        if(titulo != null){
            Page<Postagem> postagens = postagemRepository.findByTitulo(titulo, paginacao);
            return PostagemDTO.converter(postagens);
        }
        Page<Postagem> postagens = postagemRepository.findAll(paginacao);
        return PostagemDTO.converter(postagens);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PostagemDTO> cadastrar(@RequestBody @Valid PostagemForm postagemForm, UriComponentsBuilder uriBuilder){
        Postagem postagem = postagemForm.converter(usuarioRepository);
        postagemRepository.save(postagem);

        URI uri = uriBuilder.path("/api/v1/postagem/{id}").buildAndExpand(postagem.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostagemDTO(postagem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemDTO> detalhar(@PathVariable Long id){
        Optional<Postagem> postagem = postagemRepository.findById(id);
        if(postagem.isPresent()){
            return ResponseEntity.ok(new PostagemDTO(postagem.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PostagemForm postagemForm){
        Optional<Postagem> postagem = postagemRepository.findById(id);
        if(postagem.isPresent()){
            Postagem postagemAtualizada = postagemForm.atualizar(postagem.get().getId(), postagemRepository, usuarioRepository);
            return ResponseEntity.ok(new PostagemDTO(postagemAtualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Postagem> postagem = postagemRepository.findById(id);
        if(postagem.isPresent()){
            postagemRepository.deleteById(postagem.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}

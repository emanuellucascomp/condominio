package br.com.embole.condominio.controller;

import br.com.embole.condominio.config.security.TokenService;
import br.com.embole.condominio.controller.dto.AutenticacaoDTO;
import br.com.embole.condominio.controller.form.AutenticacaoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<AutenticacaoDTO> autenticar(@RequestBody @Valid AutenticacaoForm autenticacaoForm){
        UsernamePasswordAuthenticationToken dados = autenticacaoForm.toDado();
        try{
            Authentication authentication = authManager.authenticate(dados);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new AutenticacaoDTO(token, "Bearer"));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}

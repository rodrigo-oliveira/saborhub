package com.saborhub.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saborhub.config.TokenService;
import com.saborhub.domain.entities.usuario.AutenticacaoDto;
import com.saborhub.domain.entities.usuario.LoginResponseDto;
import com.saborhub.infrastructure.persistence.UsuarioEntity;

@RestController
@RequestMapping("/autenticacao")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/entrar")
    public ResponseEntity<LoginResponseDto> entrar(@RequestBody AutenticacaoDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
package com.saborhub.infra.controller;

import com.saborhub.application.dto.AutenticacaoDto;
import com.saborhub.application.dto.LoginResponseDto;
import com.saborhub.application.dto.LogoutResponseDto;
import com.saborhub.infra.config.TokenService;
import com.saborhub.infra.persistence.UsuarioEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/entrar")
    public ResponseEntity<LoginResponseDto> entrar(@RequestBody AutenticacaoDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }
    
    @PostMapping("/sair")
    public ResponseEntity<LogoutResponseDto> sair(HttpServletRequest request) {
        String token = recoverToken(request);
        
        if (token != null && !token.isEmpty()) {
            tokenService.invalidateToken(token);
            return ResponseEntity.ok(new LogoutResponseDto("Logout realizado com sucesso"));
        }
        
        return ResponseEntity.badRequest().body(new LogoutResponseDto("Token não encontrado"));
    }
    
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
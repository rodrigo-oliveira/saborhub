package com.saborhub.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.saborhub.config.TokenService;
import com.saborhub.domain.entities.usuario.AutenticacaoDto;
import com.saborhub.domain.entities.usuario.LoginResponseDto;
import com.saborhub.domain.entities.usuario.RegistroUsuarioDto;
import com.saborhub.infrastructure.persistence.UsuarioEntity;
import com.saborhub.infrastructure.persistence.UsuarioRepository;

@RestController
@RequestMapping("autenticacao")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/entrar")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AutenticacaoDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Void> register(@RequestBody RegistroUsuarioDto data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsuarioEntity newUser = new UsuarioEntity(
            data.nome(),
            data.email(),
            data.login(),
            encryptedPassword,
            java.time.ZonedDateTime.now(),
            data.role(),
            data.endereco()
        );

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
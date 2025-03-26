package br.com.prototipo.projetoMurakami.controller;

import br.com.prototipo.projetoMurakami.domain.Usuario;
import br.com.prototipo.projetoMurakami.domain.enuns.TipoConta;
import br.com.prototipo.projetoMurakami.dtos.login.CriarContaDTO;
import br.com.prototipo.projetoMurakami.dtos.login.UsuarioDTO;
import br.com.prototipo.projetoMurakami.infra.JwtUtil;
import br.com.prototipo.projetoMurakami.services.UsuarioService;
import br.com.prototipo.projetoMurakami.utils.UsuarioUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;
    private final UsuarioUtil usuarioUtil;

    @Operation(
            summary = "Fazer Login",
            description = "Permite que o usuário faça o Login"
    )
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {

        var user = usuarioService.findByEmailAndSenha(email, password);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.get().getLogin().getEmail(), password)
        );

        String token = jwtUtil.generateToken(email);

        Cookie cookie = new Cookie("Authorization", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Altere para true em produção
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setMaxAge(60 * 60); // 1 hora

        response.addCookie(cookie);

        return ResponseEntity.ok("Login realizado com sucesso!");
    }

    @Operation(
            summary = "Logout",
            description = "Permite que o usuário deslogue da conta"
    )
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("Authorization", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("Logout realizado com sucesso!");
    }


    @Operation(
            summary = "Criar Conta",
            description = "Endpoint para cadastrar uma nova conta"
    )
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CriarContaDTO loginDTO, @RequestParam TipoConta tipoConta ) {
        var user = usuarioService.criarConta(loginDTO,tipoConta);
        return ResponseEntity.created(URI.create("/users/"+ user.getId())).build();
    }

    @Operation(
            summary = "Buscar usuário",
            description = "Endpoint para buscar o usuário logado"
    )
    @GetMapping("/get/user")
    public String getUser() {
        return usuarioUtil.getUsuarioLogado();
    }

    @Operation(
            summary = "Buscar usuario logado",
            description = "Endpoint para buscar informações do usuário"
    )
    @GetMapping
    public UsuarioDTO getUsuarioLogado() {
        return usuarioUtil.getUsuario();
    }
}
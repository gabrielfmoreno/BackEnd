package com.example.final_frame.controller;

import com.example.final_frame.model.Usuario;
import com.example.final_frame.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // libera requisições do React
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    @Autowired
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoint de registro
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.register(usuario);
            // Retorna o usuário criado como JSON
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            // Retorna mensagem de erro caso já exista ou outro problema
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            boolean autenticado = usuarioService.login(usuario);
            if (autenticado) {
                return ResponseEntity.ok("Login realizado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos!");
            }
        } catch (Exception e) {
            // Em caso de erro inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor: " + e.getMessage());
        }
    }
}

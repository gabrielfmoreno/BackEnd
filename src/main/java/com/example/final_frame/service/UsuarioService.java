package com.example.final_frame.service;

import com.example.final_frame.model.Usuario;
import com.example.final_frame.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrar usuário
    public Usuario register(Usuario usuario) throws Exception {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new Exception("Username já existe!");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    // Login do usuário
    public boolean login(Usuario usuario) {
        Optional<Usuario> u = usuarioRepository.findByUsername(usuario.getUsername());
        return u.isPresent() && passwordEncoder.matches(usuario.getPassword(), u.get().getPassword());
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Atualizar usuário
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
        usuario.setUsername(usuarioAtualizado.getUsername());
        if (!usuarioAtualizado.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioAtualizado.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    // Deletar usuário
    public void deletarUsuario(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }
}

package com.morales.appserver.service;

import com.morales.appserver.model.Usuario;
import com.morales.appserver.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardarUsuario (Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}

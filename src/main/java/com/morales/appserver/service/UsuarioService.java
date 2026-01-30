package com.morales.appserver.service;

import com.morales.appserver.model.Usuario;
import com.morales.appserver.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioService (UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardarUsuario (Usuario usuario) {

        String contrasenia_hasheada = passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(contrasenia_hasheada);
        return usuarioRepository.save(usuario);
    }
}

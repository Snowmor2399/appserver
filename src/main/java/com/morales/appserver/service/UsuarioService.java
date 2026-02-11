package com.morales.appserver.service;

import com.morales.appserver.model.Usuario;
import com.morales.appserver.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService (UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario guardarUsuario (Usuario usuario) {

        String contrasenia_hasheada = passwordEncoder.encode(usuario.getContrasenia());
        usuario.setContrasenia(contrasenia_hasheada);
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String identificador, String password) {
        // Buscamos al usuario por Email O por Nombre
        return usuarioRepository.findByEmailOrNombre_usuario(identificador, identificador)
                .filter(user -> user.getContrasenia().equals(password)) // Aquí deberías usar passwordEncoder.matches si están encriptadas
                .orElse(null);
    }
}

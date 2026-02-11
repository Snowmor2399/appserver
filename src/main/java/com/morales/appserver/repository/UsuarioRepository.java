package com.morales.appserver.repository;

import com.morales.appserver.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailOrNombre_usuario(String email, String nombreUsuario);
}

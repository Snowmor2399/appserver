package com.morales.appserver.controller;

import com.morales.appserver.model.AuthResponse;
import com.morales.appserver.model.LoginRequest;
import com.morales.appserver.model.Usuario;
import com.morales.appserver.security.JwtUtil;
import com.morales.appserver.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }



    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            // 1. Guardamos el usuario (el Service se encarga de encriptar la clave)
            Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);

            // 2. Generamos el token con el email del usuario
            String token = jwtUtil.generateToken(nuevoUsuario.getEmail());

            // 3. Devolvemos el "paquete" con Token + Datos del Usuario
            return ResponseEntity.ok(new AuthResponse(token, nuevoUsuario));

        } catch (Exception e) {
            // En caso de error (email duplicado, etc.), devolvemos un error 400
            return ResponseEntity.badRequest().body("Error en el registro: " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioService.autenticar(
                loginRequest.getIdentificador(),
                loginRequest.getContrasenia()
        );

        if (usuario != null) {
            // Si el usuario existe y la clave coincide, generamos token
            String token = jwtUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, usuario));
        } else {
            // Si algo falla, devolvemos 401 (No autorizado)
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}

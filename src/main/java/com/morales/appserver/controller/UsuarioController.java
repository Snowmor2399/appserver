package com.morales.appserver.controller;

import com.morales.appserver.model.Usuario;
import com.morales.appserver.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public Usuario registrar (@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }
}

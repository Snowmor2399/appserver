package com.morales.appserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="registro_usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 200)
    private String contrasenia;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, updatable = false)
    private Instant fechaDeCreacion;

    public Usuario () {}

    public Usuario (String nombre_usuario, String contrasenia, String email) {
        this.nombreUsuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    @PrePersist
    protected void antesDeCrear(){
        this.fechaDeCreacion = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombreUsuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombreUsuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


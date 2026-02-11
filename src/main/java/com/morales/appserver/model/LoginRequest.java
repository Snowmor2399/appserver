package com.morales.appserver.model;

public class LoginRequest {
    private String identificador; // Aquí llegará el Email O el Nombre
    private String contrasenia;

    // Getters y Setters
    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
}
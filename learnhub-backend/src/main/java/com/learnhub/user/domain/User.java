package com.learnhub.user.domain;

import java.time.LocalDate;

public class User {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private UserRole rol;
    private UserStatus estado;
    private LocalDate fechaAlta;

    public User() {}

    public User(String nombre, String apellidos, String email, String password, UserRole rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.estado = UserStatus.ACTIVO;
        this.fechaAlta = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UserRole getRol() { return rol; }
    public void setRol(UserRole rol) { this.rol = rol; }

    public UserStatus getEstado() { return estado; }
    public void setEstado(UserStatus estado) { this.estado = estado; }

    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
}

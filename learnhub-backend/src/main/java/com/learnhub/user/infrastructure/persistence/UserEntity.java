package com.learnhub.user.infrastructure.persistence;

import com.learnhub.user.domain.User;
import com.learnhub.user.domain.UserRole;
import com.learnhub.user.domain.UserStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus estado;

    @Column(name = "fecha_alta", nullable = false, updatable = false)
    private LocalDate fechaAlta;

    public UserEntity() {}

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

package com.SysVentasJava.SysVentasJava.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de usuario es requerido")
    private String username;

    @NotBlank(message = "La contraseña es requerido")
    private String password;

    private int status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre de usuario es requerido") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "El nombre de usuario es requerido") String username) {
        this.username = username;
    }

    public @NotBlank(message = "La contraseña es requerido") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "La contraseña es requerido") String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}

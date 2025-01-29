package com.example.crud_basico.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 15)
    @NotNull
    @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "El DNI no es valido")
    @Column(name = "dni", nullable = false, length = 15)
    private String dni;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "El email no es válido.")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 255)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{4,12}$", message = "La contraseña debe tener entre 4 y 12 caracteres alfanuméricos.")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @ColumnDefault("NULL")
    @Column(name = "penalizacion_hasta")
    private Date penalizacion_hasta;

    public enum TipoUsuario{
        normal, administrador;
    }
}


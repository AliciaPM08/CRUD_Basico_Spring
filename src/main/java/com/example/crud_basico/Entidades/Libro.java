package com.example.crud_basico.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @NotNull
    @Size(max = 20)
    @Pattern(regexp = "^(978|979)-?\\d{1,5}-?\\d{1,7}-?\\d{1,6}-?\\d$", message = "El ISBN debe ser un código válido de 13 dígitos.")
    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @NotNull
    @Size(max = 200)
    @NotEmpty
    @Pattern(regexp = "^[a-zA-A0-9\\s]{1,200}$", message = "El titulo solo puede contener caracteres alfanumericos")
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @NotNull
    @Size(max = 100)
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9\\s]{1,200}$", message = "El autor solo puede tener caracteres alfanumericos")
    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro() {
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
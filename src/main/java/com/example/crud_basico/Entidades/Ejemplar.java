package com.example.crud_basico.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @Size(max = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Size(max = 20)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    private Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoEjemplar estado;

    public enum EstadoEjemplar{
    Disponible, Dañado, Prestado;
    }

    public Ejemplar(Integer id, Libro isbn) {
        this.id = id;
        this.isbn = isbn;
    }

    public Ejemplar() {
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", isbn=" + isbn +
                '}';
    }
}

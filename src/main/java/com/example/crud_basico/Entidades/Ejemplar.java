package com.example.crud_basico.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    private Libro isbn;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoEjemplar estado;

    @OneToMany(mappedBy = "ejemplar")
    private Set<Prestamo> prestamos = new LinkedHashSet<>();

    //Getters y setters
    public @NotNull Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotNull Libro isbn) {
        this.isbn = isbn;
    }

    public enum EstadoEjemplar{
        DISPONIBLE, DAÑADO;
    }
}

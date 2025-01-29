package com.example.crud_basico.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;


@Getter
@Setter
@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ejemplar_id", nullable = false)
    private Ejemplar ejemplar;

    @Column (name = "fecha_inicio", nullable = false)
    private Date fecha_inicio;

    @Column (name = "fecha_devolucion")
    private Date fecha_devolucion;

    public Prestamo(Integer id, Usuario usuario, Ejemplar ejemplar, Date fecha_inicio, Date fecha_devolucion) {
        this.id = id;
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.fecha_inicio = fecha_inicio;
        this.fecha_devolucion = fecha_devolucion;
    }

    public Prestamo() {
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", ejemplar=" + ejemplar +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_devolucion=" + fecha_devolucion +
                '}';
    }
}
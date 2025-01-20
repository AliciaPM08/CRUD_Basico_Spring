package com.example.crud_basico.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "prestamo")
public class Prestamo {
    //Variables
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

    @NotNull
    @Column(name = "fechaInicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fechaDevolucion")
    private Date fechaDevolucion;

    //Getters y setters
    public @NotNull Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull Usuario usuario) {
        this.usuario = usuario;
    }

    public @NotNull Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(@NotNull Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public @NotNull Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(@NotNull Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
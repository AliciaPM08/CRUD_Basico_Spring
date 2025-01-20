package com.example.crud_basico.Repositorios;

import com.example.crud_basico.Entidades.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepositorio extends JpaRepository<Ejemplar, Integer> {
}

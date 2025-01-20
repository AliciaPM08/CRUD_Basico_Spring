package com.example.crud_basico.Repositorios;

import com.example.crud_basico.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, String> {
}

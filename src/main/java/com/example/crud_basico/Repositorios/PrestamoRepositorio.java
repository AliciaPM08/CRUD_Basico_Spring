package com.example.crud_basico.Repositorios;

import com.example.crud_basico.Entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
}

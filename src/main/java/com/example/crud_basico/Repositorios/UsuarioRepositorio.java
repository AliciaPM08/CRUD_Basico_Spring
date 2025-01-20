package com.example.crud_basico.Repositorios;

import com.example.crud_basico.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository <Usuario, Integer> {
}

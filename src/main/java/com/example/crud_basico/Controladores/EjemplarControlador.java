package com.example.crud_basico.Controladores;


import com.example.crud_basico.Entidades.Ejemplar;
import com.example.crud_basico.Repositorios.EjemplarRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarControlador {
    private final EjemplarRepositorio ejemplarRepositorio;

    public EjemplarControlador(EjemplarRepositorio ejemplarRepository) {
        this.ejemplarRepositorio = ejemplarRepository;
    }

    @GetMapping
    public List<Ejemplar> getAllEjemplares() {
        return ejemplarRepositorio.findAll();
    }

    @PostMapping
    public Ejemplar createEjemplar(@RequestBody Ejemplar ejemplar) {
        return ejemplarRepositorio.save(ejemplar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> getEjemplarById(@PathVariable Integer id) {
        return ejemplarRepositorio.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> updateEjemplar(@PathVariable Integer id, @RequestBody Ejemplar ejemplarParametros) {
        return ejemplarRepositorio.findById(id).map(ejemplar -> {
            ejemplar.setEstado(ejemplarParametros.getEstado());
            ejemplar.setIsbn(ejemplarParametros.getIsbn());
            return ResponseEntity.ok(ejemplarRepositorio.save(ejemplar));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEjemplar(@PathVariable Integer id) {
        if (ejemplarRepositorio.existsById(id)) {
            ejemplarRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

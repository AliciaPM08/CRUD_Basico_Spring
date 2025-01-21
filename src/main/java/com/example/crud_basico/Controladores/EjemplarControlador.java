package com.example.crud_basico.Controladores;


import com.example.crud_basico.Entidades.Ejemplar;
import com.example.crud_basico.Entidades.Libro;
import com.example.crud_basico.Repositorios.EjemplarRepositorio;
import com.example.crud_basico.Repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")
public class EjemplarControlador {
    EjemplarRepositorio repositorioEjemplares;
    LibroRepositorio repositorioLibros;

    @Autowired
    public EjemplarControlador(EjemplarRepositorio repositorioEjemplares, LibroRepositorio repositorioLibros) {
        this.repositorioEjemplares = repositorioEjemplares;
        this.repositorioLibros = repositorioLibros;
    }

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Ejemplar>> getEjemplares(){
        List<Ejemplar> lista = this.repositorioEjemplares.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Ejemplar> getEjemplar(@PathVariable Integer id){
        Ejemplar e = this.repositorioEjemplares.findById(id).get();
        return ResponseEntity.ok(e);
    }

    //POST --> INSERT
    @PostMapping("/ejemplar")
    public ResponseEntity<Ejemplar> addEjemplar(@Valid @RequestBody Ejemplar ejemplar){
        Ejemplar ejemplarPersistido = this.repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    // POST con Form normal
    @PostMapping(value = "/ejemplarForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ejemplar> addEjemplarForm(
            @RequestParam Integer id,
            @RequestParam String estado,
            @RequestParam String isbn
    ) {
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setId(id);
        ejemplar.setEstado(Ejemplar.EstadoEjemplar.valueOf(estado.toUpperCase()));

        // Asignamos el libro al ejemplar
        Libro libro = repositorioLibros.findById(isbn).orElse(null);

        ejemplar.setIsbn(libro);

        Ejemplar ejemplarPersistido = this.repositorioEjemplares.save(ejemplar);
        return ResponseEntity.created(null).body(ejemplarPersistido);
    }

    //PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> updateEjemplar(@RequestBody Ejemplar ejemplar, @PathVariable Integer id){
        Ejemplar ejemplarPersistido = repositorioEjemplares.save(ejemplar);
        return ResponseEntity.ok().body(ejemplarPersistido);
    }

    //DELETE --> ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEjemplar(@PathVariable Integer id){
        repositorioEjemplares.deleteById(id);
        return ResponseEntity.ok().body("Ejemplar con id: " + id + " eliminado");
    }
}

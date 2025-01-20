package com.example.crud_basico.Controladores;

import com.example.crud_basico.Entidades.Libro;
import com.example.crud_basico.Repositorios.LibroRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroControlador {
    private final LibroRepositorio libroRepositorio;

    public LibroControlador(LibroRepositorio libroRepository) {
        this.libroRepositorio = libroRepository;
    }

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepositorio.findAll();
    }

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepositorio.save(libro);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> getLibroByIsbn(@PathVariable String isbn) {
        return libroRepositorio.findById(isbn).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Libro> updateLibro(@PathVariable String isbn, @RequestBody Libro libroParametros) {
        return libroRepositorio.findById(isbn).map(libro -> {
            libro.setTitulo(libroParametros.getTitulo());
            libro.setAutor(libroParametros.getAutor());
            return ResponseEntity.ok(libroRepositorio.save(libro));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String isbn) {
        if (libroRepositorio.existsById(isbn)) {
            libroRepositorio.deleteById(isbn);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

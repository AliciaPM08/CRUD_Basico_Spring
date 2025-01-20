package com.example.crud_basico.Controladores;

import com.example.crud_basico.Entidades.Prestamo;
import com.example.crud_basico.Repositorios.PrestamoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamosControlador {
    private final PrestamoRepositorio prestamoRepositorio;

    public PrestamosControlador(PrestamoRepositorio prestamoRepository) {
        this.prestamoRepositorio = prestamoRepository;
    }

    @GetMapping
    public List<Prestamo> getAllPrestamos() {
        return prestamoRepositorio.findAll();
    }

    @PostMapping
    public Prestamo createPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoRepositorio.save(prestamo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Integer id) {
        return prestamoRepositorio.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Integer id, @RequestBody Prestamo prestamoDetails) {
        return prestamoRepositorio.findById(id).map(prestamo -> {
            prestamo.setUsuario(prestamoDetails.getUsuario());
            prestamo.setEjemplar(prestamoDetails.getEjemplar());
            prestamo.setFechaInicio(prestamoDetails.getFechaInicio());
            prestamo.setFechaDevolucion(prestamoDetails.getFechaDevolucion());
            return ResponseEntity.ok(prestamoRepositorio.save(prestamo));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Integer id) {
        if (prestamoRepositorio.existsById(id)) {
            prestamoRepositorio.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

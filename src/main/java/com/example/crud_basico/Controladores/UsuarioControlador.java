package com.example.crud_basico.Controladores;

import com.example.crud_basico.Entidades.Usuario;

import com.example.crud_basico.Repositorios.UsuarioRepositorio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioControlador(UsuarioRepositorio usuarioRepository) {
        this.usuarioRepositorio = usuarioRepository;
    }

    //GET --> SELECT *
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> lista = this.usuarioRepositorio.findAll();
        System.out.println(lista);
        return ResponseEntity.ok(lista);
    }

    //GET BY ID --> SELECT BY ID
    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario u = this.usuarioRepositorio.findById(id).get();
        return ResponseEntity.ok(u);
    }

    //POST --> INSERT
    @PostMapping("/usuario")
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioPersistido = this.usuarioRepositorio.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //POST con Form normal, se trabajará con JSONs normalmente...
    @PostMapping(value = "/usuarioForm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Usuario> addUsuarioForm(@RequestParam String dni,
                                                  @RequestParam String nombre,
                                                  @RequestParam String email,
                                                  @RequestParam String password,
                                                  @RequestParam String tipo) {

            //Convertir String a TipoUsuario (enum)
            Usuario.TipoUsuario tipoUsuario = Usuario.TipoUsuario.valueOf(tipo.toUpperCase());

            Usuario usuario = new Usuario();
            usuario.setDni(dni);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setTipo(tipoUsuario);

            this.usuarioRepositorio.save(usuario);
            return ResponseEntity.created(null).body(usuario);
    }

    //PUT --> UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
        Usuario usuarioPersistido = usuarioRepositorio.save(usuario);
        return ResponseEntity.ok().body(usuarioPersistido);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        usuarioRepositorio.deleteById(id);
        String mensaje = "Usuario borrado";
        return ResponseEntity.ok().body(mensaje);
    }
}

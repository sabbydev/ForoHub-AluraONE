package com.forohub.api.controller;

import com.forohub.api.dominio.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepositorio topicoRepositorio;

    @PostMapping
    public ResponseEntity<RespuestaTopicoDTO> registrarTopico(@RequestBody @Valid SolicitudTopicoDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepositorio.save(new Topico(datos));
        RespuestaTopicoDTO respuesta = new RespuestaTopicoDTO(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<RespuestaListadoTopicoDTO>> mostrarTopicos() {
        return ResponseEntity.ok(topicoRepositorio.findAll().stream().map(RespuestaListadoTopicoDTO::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoDTO> detallarTopico(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        var datos = new RespuestaTopicoDTO(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
        return ResponseEntity.ok(datos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid SolicitudTopicoDTO datos) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        topico.actualizarDatos(datos);

        return ResponseEntity.ok(new RespuestaTopicoDTO(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(),topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        if (!topicoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        topicoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

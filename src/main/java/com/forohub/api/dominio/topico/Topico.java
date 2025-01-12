package com.forohub.api.dominio.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(
        name = "topicos",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"titulo", "mensaje"}
        )
)
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topico")
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String curso;

    public Topico(@Valid SolicitudTopicoDTO datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Boolean.TRUE;
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizarDatos(@Valid SolicitudTopicoDTO datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();
    }
}
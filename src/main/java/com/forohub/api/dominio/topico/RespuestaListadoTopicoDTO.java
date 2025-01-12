package com.forohub.api.dominio.topico;

import java.time.LocalDateTime;

public record RespuestaListadoTopicoDTO(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String autor,
        String curso
) {
    public RespuestaListadoTopicoDTO(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getEstado(), topico.getAutor(), topico.getCurso());
    }
}

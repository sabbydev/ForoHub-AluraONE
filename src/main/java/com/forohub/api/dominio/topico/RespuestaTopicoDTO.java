package com.forohub.api.dominio.topico;

import java.time.LocalDateTime;

public record RespuestaTopicoDTO(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String autor,
        String curso
) {
}

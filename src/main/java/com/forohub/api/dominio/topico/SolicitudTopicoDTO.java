package com.forohub.api.dominio.topico;

import jakarta.validation.constraints.NotBlank;

public record SolicitudTopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}

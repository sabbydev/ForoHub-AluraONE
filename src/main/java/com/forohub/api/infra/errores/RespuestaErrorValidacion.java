package com.forohub.api.infra.errores;

import org.springframework.validation.FieldError;

public record RespuestaErrorValidacion(String campo, String error) {
    public RespuestaErrorValidacion(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}

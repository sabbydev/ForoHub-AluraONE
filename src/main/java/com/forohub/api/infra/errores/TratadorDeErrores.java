package com.forohub.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> tratarError400(MethodArgumentTypeMismatchException e){
        return ResponseEntity.badRequest().body(String.format("El parámetro '%s' debe ser de tipo '%s'.",
                e.getName(), Objects.requireNonNull(e.getRequiredType()).getSimpleName()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RespuestaErrorValidacion>> tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(RespuestaErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> tratarError409(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Ocurrió un conflicto al intentar procesar la solicitud.");
    }
}

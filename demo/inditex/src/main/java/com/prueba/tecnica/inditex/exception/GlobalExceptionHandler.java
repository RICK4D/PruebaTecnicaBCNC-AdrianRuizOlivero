package com.prueba.tecnica.inditex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de excepciones para entidad no encontrada (Ej: Price no encontrado)

    /**
     * Se encarga de las excepcions del tipo 'EntityNotfoundException', que puede darse cuando pej un precion no es
     * encontrado en BBDD.
     * @param ex
     * @return 404 NOT Found
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Manejo de excepciones relacionadas con la validación de argumentos. Pej: cuando un DTO no cumple las
     * restricciones definidas (@NotNull, @Size, ...)
     * @param ex
     * @return 400 Bad Request y un body que contiene un mapa entradas tipo nombreAtributo-errorValidacion
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage(),
                        (existing, replacement) -> existing
                ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Captura cualquier otra excepción que no haya sido manejada previamente.
     * @param ex
     * @param request
     * @return 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado: " + ex.getMessage());
    }
}

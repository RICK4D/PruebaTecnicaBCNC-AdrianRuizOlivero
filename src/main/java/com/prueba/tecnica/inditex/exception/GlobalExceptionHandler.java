package com.prueba.tecnica.inditex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle method missing servlet request parameter exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMethodMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Invalid parameter type");
        errorDetails.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    /**
     * Handle method date time parse exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> handleMethodDateTimeParseException(DateTimeParseException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", "Invalid date parsing");
        errorDetails.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    /**
     * Captura cualquier otra excepción que no haya sido manejada previamente.
     *
     * @param ex      the ex
     * @return 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
    }
}

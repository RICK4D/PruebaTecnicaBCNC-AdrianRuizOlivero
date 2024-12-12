package com.prueba.tecnica.inditex.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
     * @param request the request
     * @return 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + ex.getMessage());
    }
}

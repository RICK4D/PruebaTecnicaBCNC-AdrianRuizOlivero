package com.prueba.tecnica.inditex.exception;

/**
 * The type Entity not found exception.
 */
public class EntityNotFoundException extends RuntimeException {
    /**
     * Instantiates a new EntityNotFoundException.
     *
     * @param message the message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}

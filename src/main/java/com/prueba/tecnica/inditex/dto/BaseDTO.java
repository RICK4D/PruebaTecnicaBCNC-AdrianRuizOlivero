package com.prueba.tecnica.inditex.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

/**
 * The type Base dto.
 */
@Setter
@Getter
@SuperBuilder
@MappedSuperclass
public abstract class BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The Id.
     */
    @NotNull
    protected Long id;

    /**
     * Instantiates a new Base dto.
     */
    protected BaseDTO() {
    }
}

package com.prueba.tecnica.inditex.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.io.Serial;
import java.io.Serializable;


/**
 * The type Base entity.
 */
@Setter
@Getter
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    protected Long id;

    /**
     * Instantiates a new Base entity.
     */
    protected BaseEntity() {
    }
}
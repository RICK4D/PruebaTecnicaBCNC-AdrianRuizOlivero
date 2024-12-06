package com.prueba.tecnica.inditex.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@SuperBuilder
public abstract class BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    protected Long id;

    protected BaseDTO() {
    }
}

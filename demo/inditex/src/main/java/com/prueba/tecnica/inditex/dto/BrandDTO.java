package com.prueba.tecnica.inditex.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class BrandDTO extends BaseDTO {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    public BrandDTO() {
        super();
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

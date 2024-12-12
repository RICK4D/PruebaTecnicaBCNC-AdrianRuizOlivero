package com.prueba.tecnica.inditex.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Product dto.
 */
@Setter
@Getter
@SuperBuilder
public class ProductDTO extends BaseDTO {
    @NotNull
    @Size(min = 2, max = 100)
    private String name;
    @Size(max = 255)
    private String description;
    @NotNull
    @Size(min = 3, max = 50)
    private String category;

    /**
     * Instantiates a new Product dto.
     */
    public ProductDTO() {
        super();
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

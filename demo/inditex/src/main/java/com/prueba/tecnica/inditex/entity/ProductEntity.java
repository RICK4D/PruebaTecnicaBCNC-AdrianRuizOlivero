package com.prueba.tecnica.inditex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Product entity.
 */
@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    /**
     * Instantiates a new Product entity.
     */
    public ProductEntity() {
        super();
    }

    /**
     * Instantiates a new Product entity.
     *
     * @param name        the name
     * @param description the description
     * @param category    the category
     */
    public ProductEntity(String name, String description, String category) {
        super();
        this.name = name;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}


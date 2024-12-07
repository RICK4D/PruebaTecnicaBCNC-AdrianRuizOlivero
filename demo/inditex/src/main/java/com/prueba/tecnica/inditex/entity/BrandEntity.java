package com.prueba.tecnica.inditex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * The type Brand entity.
 */
@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name; // Nombre de la marca

    /**
     * Instantiates a new Brand entity.
     */
    public BrandEntity() {
        super();
    }

    /**
     * Instantiates a new Brand entity.
     *
     * @param name the name
     */
    public BrandEntity(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.prueba.tecnica.inditex.copier;

import com.prueba.tecnica.inditex.dto.BaseDTO;
import com.prueba.tecnica.inditex.entity.BaseEntity;

import java.util.List;
import java.util.Objects;

public abstract class AbstractCopier<E extends BaseEntity, D extends BaseDTO> {
    /**
     * Método para convertir una entidad a DTO (abstracto)
     * @param entity
     * @return
     */
    public abstract D toDTO(E entity);

    /**
     * Método para convertir un DTO a una entidad (abstracto)
     * @param dto
     * @return
     */
    public abstract E toEntity(D dto);

    /**
     * Método para convertir una lista de entidades a una lista de DTOs
     * @param entities
     * @return
     */
    public List<D> toDTOList(List<E> entities) {
        return entities.stream()
                .filter(Objects::nonNull) // Evita posibles valores null
                .map(this::toDTO)
                .toList();
    }

    // Método para convertir una lista de DTOs a una lista de entidades
    public List<E> toEntityList(List<D> dtos) {
        return dtos.stream()
                .filter(Objects::nonNull) // Evita posibles valores null
                .map(this::toEntity)
                .toList();
    }
}


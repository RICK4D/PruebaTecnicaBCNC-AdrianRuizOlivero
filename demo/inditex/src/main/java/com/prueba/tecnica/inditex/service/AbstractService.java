package com.prueba.tecnica.inditex.service;

import com.prueba.tecnica.inditex.copier.AbstractCopier;
import com.prueba.tecnica.inditex.dto.BaseDTO;
import com.prueba.tecnica.inditex.entity.BaseEntity;
import com.prueba.tecnica.inditex.exception.EntityNotFoundException;
import com.prueba.tecnica.inditex.repository.IRepository;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends BaseEntity, D extends BaseDTO> {

    protected abstract IRepository<E, Long> getRepository();
    protected abstract AbstractCopier<E, D> getCopier();

    // Guardar una entidad (desde DTO)
    public D save(@NonNull D dto) {
        E entity = getCopier().toEntity(dto);
        E savedEntity = getRepository().save(entity);
        return getCopier().toDTO(savedEntity);
    }

    // Buscar por ID (devuelve un Optional del DTO)
    public Optional<D> findById(@NonNull Long id) {
        return Optional.ofNullable(getRepository().findById(id).map(getCopier()::toDTO).
                orElseThrow(() -> new EntityNotFoundException("Price with ID: " + id + " not found")));
    }

    // Listar todas las entidades (como lista de DTOs)
    public List<D> findAll() {
        return getRepository().findAll().stream().map(getCopier()::toDTO).toList();
    }

    // Eliminar por ID
    public void deleteById(@NonNull Long id) {
        if (!getRepository().existsById(id)) {
            throw new EntityNotFoundException("Price with ID: " + id + " not found");
        }
        getRepository().deleteById(id);
    }

    // Actualizar una entidad (desde DTO)
    public D update(@NonNull D dto) {
        if (dto.getId() == null || !getRepository().existsById(dto.getId())) {
            throw new EntityNotFoundException("Price with ID: " + dto.getId() + " not found");
        }
        E entity = getCopier().toEntity(dto);
        E updatedEntity = getRepository().update(entity);
        return getCopier().toDTO(updatedEntity);
    }
}

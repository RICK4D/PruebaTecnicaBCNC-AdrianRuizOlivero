package com.prueba.tecnica.inditex.service;

import com.prueba.tecnica.inditex.copier.AbstractCopier;
import com.prueba.tecnica.inditex.dto.BaseDTO;
import com.prueba.tecnica.inditex.entity.BaseEntity;
import com.prueba.tecnica.inditex.exception.EntityNotFoundException;
import com.prueba.tecnica.inditex.repository.IRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

import static com.prueba.tecnica.inditex.exception.GlobalExceptionHandler.ENTITY_NOT_FOUND;

public abstract class AbstractService<E extends BaseEntity, D extends BaseDTO> {


    protected abstract IRepository<E, Long> getRepository();
    protected abstract AbstractCopier<E, D> getCopier();

    // Guardar una entidad (desde DTO)
    @Transactional
    public D save(@NonNull D dto) {
        E entity = getCopier().toEntity(dto);
        E savedEntity = getRepository().save(entity);
        return getCopier().toDTO(savedEntity);
    }

    // Buscar por ID (devuelve un Optional del DTO)
    @Transactional
    public Optional<D> findById(@NonNull Long id) throws EntityNotFoundException, IllegalArgumentException {
        validateEntityExists(id);
        return getRepository().findById(id).map(getCopier()::toDTO);
    }

    // Listar todas las entidades (como lista de DTOs)
    public List<D> findAll() {
        return getRepository().findAll().stream().map(getCopier()::toDTO).toList();
    }

    private void validateEntityExists(Long id) throws EntityNotFoundException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (!getRepository().existsById(id)) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, id));
        }
    }


    // Eliminar por ID
    @Transactional
    public void deleteById(@NonNull Long id) throws EntityNotFoundException, IllegalArgumentException {
        validateEntityExists(id);
        getRepository().deleteById(id);
    }

    // Actualizar una entidad (desde DTO)
    @Transactional
    public D update(@NonNull D dto) throws EntityNotFoundException, IllegalArgumentException {
        validateEntityExists(dto.getId());
        E entity = getCopier().toEntity(dto);
        E updatedEntity = getRepository().update(entity);
        return getCopier().toDTO(updatedEntity);
    }
}

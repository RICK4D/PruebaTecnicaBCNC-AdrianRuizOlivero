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

/**
 * The type Abstract service.
 *
 * @param <E> the type parameter
 * @param <D> the type parameter
 */
public abstract class AbstractService<E extends BaseEntity, D extends BaseDTO> {


    /**
     * Gets repository.
     *
     * @return the repository
     */
    protected abstract IRepository<E, Long> getRepository();

    /**
     * Gets copier.
     *
     * @return the copier
     */
    protected abstract AbstractCopier<E, D> getCopier();

    /**
     * Save a DTO.
     *
     * @param dto the dto
     * @return the d
     */
    @Transactional
    public D save(@NonNull D dto) {
        E entity = getCopier().toEntity(dto);
        E savedEntity = getRepository().save(entity);
        return getCopier().toDTO(savedEntity);
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws EntityNotFoundException  the entity not found exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Transactional
    public Optional<D> findById(@NonNull Long id) throws EntityNotFoundException, IllegalArgumentException {
        validateEntityExists(id);
        return getRepository().findById(id).map(getCopier()::toDTO);
    }

    /**
     * Find all elements.
     *
     * @return a list with all elements
     */
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


    /**
     * Delete by id.
     *
     * @param id the id
     * @throws EntityNotFoundException  the entity not found exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Transactional
    public void deleteById(@NonNull Long id) throws EntityNotFoundException, IllegalArgumentException {
        validateEntityExists(id);
        getRepository().deleteById(id);
    }

    /**
     * Update an existing element.
     *
     * @param dto the dto
     * @return the d
     * @throws EntityNotFoundException  the entity not found exception
     * @throws IllegalArgumentException the illegal argument exception
     */
// Actualizar una entidad (desde DTO)
    @Transactional
    public D update(@NonNull D dto) throws EntityNotFoundException, IllegalArgumentException {
        validateEntityExists(dto.getId());
        E entity = getCopier().toEntity(dto);
        E updatedEntity = getRepository().update(entity);
        return getCopier().toDTO(updatedEntity);
    }
}

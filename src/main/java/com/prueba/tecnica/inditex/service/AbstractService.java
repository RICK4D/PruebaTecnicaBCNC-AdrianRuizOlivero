package com.prueba.tecnica.inditex.service;

import com.prueba.tecnica.inditex.copier.AbstractCopier;
import com.prueba.tecnica.inditex.dto.BaseDTO;
import com.prueba.tecnica.inditex.entity.BaseEntity;
import com.prueba.tecnica.inditex.exception.EntityNotFoundException;
import com.prueba.tecnica.inditex.repository.IRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.dao.OptimisticLockingFailureException;

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
     * @throws IllegalArgumentException the illegal argument exception
     * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has
     * a version attribute with a different value from that found in the persistence store.
     */
    @Transactional
    public D save(@NonNull D dto) throws IllegalArgumentException, OptimisticLockingFailureException {
        E entity = getCopier().toEntity(dto);
        E savedEntity = getRepository().save(entity);
        return getCopier().toDTO(savedEntity);
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Optional<D> findById(@NonNull Long id) throws IllegalArgumentException {
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


    /**
     * Delete by id.
     *
     * @param id the id
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Transactional
    public void deleteById(@NonNull Long id) throws IllegalArgumentException {
        getRepository().deleteById(id);
    }

    /**
     * Update an existing element.
     *
     * @param dto the dto
     * @return the d
     * @throws IllegalArgumentException the illegal argument exception
     * @throws OptimisticLockingFailureException when at least one entity uses optimistic locking and has
     * a version attribute with a different value from that found in the persistence store.
     */
    @Transactional
    public D update(@NonNull D dto) throws IllegalArgumentException, OptimisticLockingFailureException {
        E entity = getCopier().toEntity(dto);
        E updatedEntity = getRepository().update(entity);
        return getCopier().toDTO(updatedEntity);
    }
}

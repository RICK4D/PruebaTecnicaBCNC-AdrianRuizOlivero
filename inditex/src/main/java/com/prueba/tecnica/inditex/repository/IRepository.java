package com.prueba.tecnica.inditex.repository;

import com.prueba.tecnica.inditex.entity.BaseEntity;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Repository.
 *
 * @param <E>  the type parameter
 * @param <ID> the type parameter
 */
public interface IRepository<E extends BaseEntity, ID> extends JpaRepository<E, ID> {
    /**
     * Update e.
     *
     * @param entity the entity
     * @return the e
     */
    default E update(E entity) throws IllegalArgumentException, OptimisticLockingFailureException {
        return save(entity);
    }
}

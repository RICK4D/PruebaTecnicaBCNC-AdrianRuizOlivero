package com.prueba.tecnica.inditex.repository;

import com.prueba.tecnica.inditex.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepository<E extends BaseEntity, ID> extends JpaRepository<E, ID> {
    default E update(E entity) {
        return save(entity);
    }
}

package com.josedacruz.learning.spring.backend_server.repositories;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T,ID>{
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    void deleteById(ID id);
    void update(T entity);
}

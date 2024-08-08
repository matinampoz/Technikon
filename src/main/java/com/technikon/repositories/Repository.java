package com.technikon.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

    Optional<T> findById(K id);

    List<T> findAll();

    Optional<T> save(T t);

    boolean deleteById(K id);
}

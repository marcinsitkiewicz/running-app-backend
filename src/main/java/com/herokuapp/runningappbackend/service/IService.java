package com.herokuapp.runningappbackend.service;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {

    Collection<T> getAll();
    Optional<T> get(Long id);
}

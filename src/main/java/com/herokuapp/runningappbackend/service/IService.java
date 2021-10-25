package com.herokuapp.runningappbackend.service;

import java.util.Collection;

public interface IService<T> {

    Collection<T> getAll();
    T get(Long id);
}

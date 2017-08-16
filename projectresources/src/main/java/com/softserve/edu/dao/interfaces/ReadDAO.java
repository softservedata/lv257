package com.softserve.edu.dao.interfaces;

import java.util.List;

public interface ReadDAO<E> {

    E getById(Long id);

    List<E> getAll();
}

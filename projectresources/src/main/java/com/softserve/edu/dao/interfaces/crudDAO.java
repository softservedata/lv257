package com.softserve.edu.dao.interfaces;

public interface crudDAO<E> extends ReadDAO {

    void add(E element);

    void update(E element);

    void delete(E element);

    void deleteById(Long id);
}

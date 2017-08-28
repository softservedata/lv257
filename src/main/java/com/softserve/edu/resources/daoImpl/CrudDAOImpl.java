package com.softserve.edu.resources.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.softserve.edu.resources.dao.CrudDAO;

@Repository
public class CrudDAOImpl<E> implements CrudDAO<E> {
    @PersistenceContext
    private EntityManager em;

    private Class<E> elementClass;

    public CrudDAOImpl(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    @Override
    public void addElement(E element) {
        em.persist(element);
    }

    @Override
    public void updateElement(E element) {
        em.refresh(element);
    }

    @Override
    public E getElementByID(Long elementId) {
        E element = em.find(elementClass,
                elementId);
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAllElements() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = builder.createQuery(elementClass);
        Root<E> request = criteriaQuery.from(elementClass);
        criteriaQuery.select(request);
        Query query = em.createQuery(criteriaQuery);
        List<E> elements = query.getResultList();
        return elements;
    }

    @Override
    public void deleteElement(E element) {
        em.remove(element);
    }

}

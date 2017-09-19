package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceRequestDAO;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Repository("resourceRequestDAO")
@Transactional
public class ResourceRequestDAOImpl implements ResourceRequestDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persistRequest(ResourceRequest request) {
        entityManager.persist(request);
    }

    @Override
    public List<ResourceRequest> getAllRequests() {
        Query query = entityManager.createQuery("SELECT m FROM ResourceRequest m");
        List<?> result = query.getResultList();
        return (List<ResourceRequest>) result;
    }

    @Override
    public ResourceRequest findById(long id) {
        Query query = entityManager.createQuery("select i from ResourceRequest i where i.id = :id")
                .setParameter("id", id);
        ResourceRequest request = (ResourceRequest) query.getSingleResult();
        return request;
    }

    @Override
    public void updateRequest(ResourceRequest request) {
        entityManager.merge(request);
    }

}

package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceRequestDAO;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
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




    public List<ResourceRequest> getAllRequestsForOneRegister(){

        List<ResourceRequest> requests = new ArrayList<>();
        ResourceRequest requestForRegister = new ResourceRequest();

        try {
            Field entityRegisterField = ResourceRequest.class.getDeclaredField("id_from");

            Column columnRegister = entityRegisterField.getDeclaredAnnotation(Column.class);
            String columnRegisterName = columnRegister.name();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery criteriaQuery = builder.createQuery();
            Root request = criteriaQuery.from(ResourceRequest.class);

            criteriaQuery.select(request);
            criteriaQuery.where(builder.and(builder.equal(request.get(columnRegisterName),
                    requestForRegister.getRegister().getId())));
            Query query = entityManager.createQuery(criteriaQuery);
            requests = query.getResultList();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return requests;
    }

}

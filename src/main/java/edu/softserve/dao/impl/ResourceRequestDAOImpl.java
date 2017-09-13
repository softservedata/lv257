package edu.softserve.dao.impl;

import edu.softserve.dao.ResourceRequestDAO;
import edu.softserve.entity.ResourceRequest;
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
    public void updateRequest(ResourceRequest request) {
        entityManager.refresh(request);
    }

    //search on client
    public List<ResourceRequest> getAllRequests() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ResourceRequest> criteriaQuery = builder.createQuery(ResourceRequest.class);
        Root<ResourceRequest> request = criteriaQuery.from(ResourceRequest.class);

        criteriaQuery.select(request);
        Query query = entityManager.createQuery(criteriaQuery);
        List<ResourceRequest> result = query.getResultList();
        return result;
    }


    //search in db
    public List<ResourceRequest> getNewResourcesRequest() {
        List<ResourceRequest> requests = new ArrayList<>();

        Field entityStatusField = null;
        try {
            entityStatusField = ResourceRequest.class.getDeclaredField("status");

            Column columnStatus = entityStatusField.getDeclaredAnnotation(Column.class);
            String columnStatusName = columnStatus.name();

            Field entityNotifyField = ResourceRequest.class.getDeclaredField("notifyExecutor");
            Column columnNotifying = entityNotifyField.getDeclaredAnnotation(Column.class);
            String columnNotifyingName = columnNotifying.name();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery criteriaQuery = builder.createQuery();
            Root request = criteriaQuery.from(ResourceRequest.class);

            criteriaQuery.select(request);
            criteriaQuery.where(builder.and(builder.equal(request.get(columnStatusName), "NEW")
                    , builder.equal(request.get(columnNotifyingName), true)));
            Query query = entityManager.createQuery(criteriaQuery);
            requests = query.getResultList();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public List<ResourceRequest> getProcessedRequest() {
        List<ResourceRequest> requests = new ArrayList<>();
        try {
            Field entityStatusField = ResourceRequest.class.getDeclaredField("status");
            Column column = entityStatusField.getDeclaredAnnotation(Column.class);
            String columnName = column.name();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery criteriaQuery = builder.createQuery();
            Root request = criteriaQuery.from(ResourceRequest.class);

            criteriaQuery.select(request);
            criteriaQuery.where(builder.and(builder.equal(request.get(columnName), "ACCEPTED")
                    , builder.equal(request.get(columnName), "DELETED")));
            Query query = entityManager.createQuery(criteriaQuery);
            requests = query.getResultList();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return requests;
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

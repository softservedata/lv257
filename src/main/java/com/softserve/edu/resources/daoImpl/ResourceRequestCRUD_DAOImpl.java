package com.softserve.edu.resources.daoImpl;

import com.softserve.edu.resources.dao.ResourceRequestCRUD_DAO;
import com.softserve.edu.resources.entity.ResourceRequest;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class ResourceRequestCRUD_DAOImpl implements ResourceRequestCRUD_DAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateRequest(ResourceRequest request) {
        entityManager.refresh(request);
    }
}

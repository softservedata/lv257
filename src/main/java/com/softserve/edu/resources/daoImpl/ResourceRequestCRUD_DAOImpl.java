package com.softserve.edu.resources.daoImpl;

import com.softserve.edu.resources.dao.ResourceRequestCRUD_DAO;
import com.softserve.edu.resources.entity.ResourceRequest;
import org.hibernate.SessionFactory;


public class ResourceRequestCRUD_DAOImpl implements ResourceRequestCRUD_DAO {
    private static SessionFactory factory;

    @Override
    public void updateRequest(ResourceRequest request) {
        factory.getCurrentSession().update(request);
    }
}

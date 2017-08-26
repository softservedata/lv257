package com.softserve.edu.resources.daoImpl;

import com.softserve.edu.resources.dao.ResourceRequestCRUD_DAO;
import com.softserve.edu.resources.entity.ResourceRequest;
import org.hibernate.SessionFactory;

/**
 * Created by User on 26.08.2017.
 */
public class ResourceRequestCRUD_DAOimpl implements ResourceRequestCRUD_DAO {
    private static SessionFactory factory;

    @Override
    public void update(ResourceRequest request) {
        factory.getCurrentSession().update(request);
    }
}

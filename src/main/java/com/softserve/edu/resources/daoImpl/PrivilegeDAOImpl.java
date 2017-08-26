package com.softserve.edu.resources.daoImpl;


import com.softserve.edu.resources.dao.PrivilegeDAO;
import com.softserve.edu.resources.entity.Privilege;
import org.hibernate.SessionFactory;

public class PrivilegeDAOImpl implements PrivilegeDAO {


    private SessionFactory sessionFactory;

    public PrivilegeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Privilege findByName(String name) {
        return null;
    }

    public void delete(Privilege privilege) {

    }
}

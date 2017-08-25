package edu.softserve.dao.impl;

import edu.softserve.dao.RoleDAO;
import edu.softserve.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public RoleDAOImpl() {
    }

    @Override
    public Role findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Role.class);
        crit.add(Restrictions.eq("name", name));
        return (Role)crit.uniqueResult();
    }

    @Override
    public void delete(Role role) {

    }

}

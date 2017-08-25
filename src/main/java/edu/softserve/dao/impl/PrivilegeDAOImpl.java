package edu.softserve.dao.impl;

import edu.softserve.dao.PrivilegeDAO;
import edu.softserve.entity.Privilege;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
//@Transactional
public class PrivilegeDAOImpl implements PrivilegeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public PrivilegeDAOImpl() {

    }

    @Override
    public Privilege findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Privilege.class);
        crit.add(Restrictions.eq("name", name));
        return (Privilege)crit.uniqueResult();
    }

    @Override
    public void delete(Privilege privilege) {

    }
}

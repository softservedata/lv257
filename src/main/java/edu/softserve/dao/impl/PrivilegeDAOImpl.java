package edu.softserve.dao.impl;

import edu.softserve.dao.PrivilegeDAO;
import edu.softserve.entity.Privilege;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PrivilegeDAOImpl implements PrivilegeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Privilege findByName(String name) {
        Query query = entityManager.createQuery("select i from Privilege i where i.name = :name")
                .setParameter("name", name);
        Privilege privilege = (Privilege) query.getSingleResult();
        return privilege;
    }

    @Override
    public void delete(Privilege privilege) {

    }

    @Override
    public Privilege addPrivilege(Privilege privilege) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Privilege> getAllPrivileges() {
        List<?> list = entityManager.createQuery("SELECT p FROM Privilege p").getResultList();
        return (List<Privilege>) list;
    }
}

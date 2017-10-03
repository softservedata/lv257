package com.softserve.edu.Resources.security.dao.impl;

import com.softserve.edu.Resources.dao.impl.GenericDAOImpl;
import com.softserve.edu.Resources.security.dao.GenericPrivilegeDAO;
import com.softserve.edu.Resources.security.entity.GenericPrivilege;
import com.softserve.edu.Resources.security.entity.ResourcePropertyPrivilege;
import com.softserve.edu.Resources.security.entity.ResourceTypePrivilege;
import com.softserve.edu.Resources.security.entity.SystemPrivilege;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GenericPrivilegeDAOImpl extends GenericDAOImpl<GenericPrivilege, Long> implements GenericPrivilegeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public GenericPrivilegeDAOImpl() {
        super(GenericPrivilege.class);
    }

    @Override
    public void addGenericPrivilege(GenericPrivilege privilege) {
        this.makePersistent(privilege);
    }

    @Override
    public void updateGenericPrivilege(GenericPrivilege privilege) {
        this.makePersistent(privilege);
    }

    @Override
    public List<GenericPrivilege> getAllSystemPrivileges() {
        return this.findAll().stream()
                .filter(x -> x.getClass().equals(SystemPrivilege.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GenericPrivilege> getAllResourceTypePrivileges() {
        return this.findAll().stream()
                .filter(x -> x.getClass().equals(ResourceTypePrivilege.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GenericPrivilege> getAllResourcePropertyPrivileges() {
        return this.findAll().stream()
                .filter(x -> x.getClass().equals(ResourcePropertyPrivilege.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GenericPrivilege> getAllPrivileges() {
        return this.findAll().stream()
                .filter(x -> x.getClass().equals(GenericPrivilege.class))
                .collect(Collectors.toList());
    }

    @Override
    public GenericPrivilege getGenericPrivilegeById(long id) {

        //.orElse(new GenericPrivilege()); cannot create abstract object
        return this.findById(id).orElse(null);
    }
}

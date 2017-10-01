package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ResourceRequestDAOImpl extends GenericDAOImpl<ResourceRequest, Long> {

    public ResourceRequestDAOImpl() {
        super(ResourceRequest.class);
    }

    @Override
    public Optional<ResourceRequest> findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public ResourceRequest makePersistent(ResourceRequest instance) {
        return super.makePersistent(instance);
    }

    @Override
    public List<ResourceRequest> findAll() {
        return super.findAll();
    }
}

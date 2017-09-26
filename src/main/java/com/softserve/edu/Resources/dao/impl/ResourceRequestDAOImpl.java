package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceRequestDAOImpl extends GenericDAOImpl<ResourceRequest, Long> {

    public ResourceRequestDAOImpl() {
        super(ResourceRequest.class);
    }
}

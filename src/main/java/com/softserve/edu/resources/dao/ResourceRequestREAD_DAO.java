package com.softserve.edu.resources.dao;

import com.softserve.edu.resources.entity.ResourceRequest;

import java.util.List;

/**
 * Created by User on 26.08.2017.
 */
public interface ResourceRequestREAD_DAO {

    public List<ResourceRequest> getAllRequests();

    public List<ResourceRequest> getNewResourcesRequest();

    public List<ResourceRequest> getProcessedRequest() ;
}

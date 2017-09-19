package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.ResourceRequest;

import java.util.List;

public interface ResourceRequestDAO {

    void persistRequest(ResourceRequest request);

    void updateRequest(ResourceRequest request);

    List<ResourceRequest> getAllRequests();

    ResourceRequest findById(long id);

}

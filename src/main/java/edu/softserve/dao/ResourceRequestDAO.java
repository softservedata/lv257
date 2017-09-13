package edu.softserve.dao;

import edu.softserve.entity.ResourceRequest;

import java.util.List;

public interface ResourceRequestDAO {

    void persistRequest(ResourceRequest request);

    void updateRequest(ResourceRequest request);

    List<ResourceRequest> getAllRequests();

    List<ResourceRequest> getNewResourcesRequest();

    List<ResourceRequest> getProcessedRequest() ;

   // List<ResourceRequest> getAllRequestsForOneRegister();

}

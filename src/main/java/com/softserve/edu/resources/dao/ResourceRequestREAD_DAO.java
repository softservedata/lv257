package com.softserve.edu.resources.dao;

import com.softserve.edu.resources.entity.ResourceRequest;

import java.util.List;


public interface ResourceRequestREAD_DAO {

     List<ResourceRequest> getAllRequests();

     List<ResourceRequest> getNewResourcesRequest();

     List<ResourceRequest> getProcessedRequest() ;

     List<ResourceRequest> getAllRequestsForOneRegister();
}

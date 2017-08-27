package com.softserve.edu.resources.service;


import com.softserve.edu.resources.dto.Message;
import com.softserve.edu.resources.entity.ResourceRequest;
import com.softserve.edu.resources.entity.User;

import java.util.List;


public interface ResourceRequestService {

     void assignResourceAdmin(ResourceRequest resourceRequest, User resourceAdmin);

     void assignRegister(ResourceRequest resourceRequest, User register);

     void accepted(ResourceRequest resourceRequest, Message message);

     void declined(ResourceRequest resourceRequest, Message message);

     void toRefinement(ResourceRequest resourceRequest, Message message);

     List<ResourceRequest> showNewRequestsByAdmin(User resourceAdmin);

     List<ResourceRequest> getResourcesRequest();

     List<ResourceRequest> getNewResourcesRequest();

     List<ResourceRequest> getProcessedResourcesRequest();

     List<ResourceRequest> getForOneRegisterAllRequests();

}

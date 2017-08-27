package com.softserve.edu.resources.service;


import com.softserve.edu.resources.dto.Message;
import com.softserve.edu.resources.entity.ResourceRequest;
import com.softserve.edu.resources.entity.User;

import java.util.List;


public interface ResourceRequestService {

    public void assignResourceAdmin(ResourceRequest resourceRequest, User resourceAdmin);

    public void accepted(ResourceRequest resourceRequest, Message message);

    public void declined(ResourceRequest resourceRequest, Message message);

    public void toRefinement(ResourceRequest resourceRequest, Message message);

    public List<ResourceRequest> showNewRequestsByAdmin(User resourceAdmin);

    public List<ResourceRequest> getResourcesRequest();

    public List<ResourceRequest> getNewResourcesRequest();

    public List<ResourceRequest> getProcessedResourcesRequest();


}

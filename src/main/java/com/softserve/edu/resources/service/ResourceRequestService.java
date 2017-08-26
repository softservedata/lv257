package com.softserve.edu.resources.service;


import com.softserve.edu.resources.dto.MessageDTO;
import com.softserve.edu.resources.dto.ResourceRequestDTO;
import com.softserve.edu.resources.dto.UserDTO;
import com.softserve.edu.resources.entity.User;

import java.util.List;

/**
 * Created by User on 25.08.2017.
 */
public interface ResourceRequestService {

    public void assignResourceAdmin(ResourceRequestDTO resourceRequest, User resourceAdmin);

    public void accepted(ResourceRequestDTO resourceRequest, MessageDTO message);

    public void declined(ResourceRequestDTO resourceRequest,MessageDTO message);

    public void toRefinement(ResourceRequestDTO resourceRequest,MessageDTO message);

    public  List<ResourceRequestDTO> showNewRequestsByAdmin(UserDTO resourceAdmin);

    public List<ResourceRequestDTO> getResourcesRequest();

    public List<ResourceRequestDTO> getNewResourcesRequest();

    public List<ResourceRequestDTO> getProcessedResourcesRequest();




}

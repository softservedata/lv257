package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.entity.Message;
import com.softserve.edu.Resources.dao.ResourceRequestDAO;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.entity.Status;
import com.softserve.edu.Resources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RequestService {

    @Autowired
    ResourceRequestDAO resourceRequestDAO;
    @Autowired
    UserDAO userDAO;

    public void fillUpRequest(ResourceRequest requestService) {

        org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        requestService.setStatus(Status.NEW);

        Date date = new Date();
        requestService.setUpdate(date);

        User user = userDAO.findByEmail(userSpring.getUsername());

        requestService.setRegister(user);

        resourceRequestDAO.persistRequest(requestService);

    }

    public void response(long requestId, Message message) {
        ResourceRequest request = resourceRequestDAO.findById(requestId);
        request.setUpdate(new Date());
        switch (message.getPurpose()) {
            case Decline: {
                request.setStatus(Status.DECLINED);
                break;
            }
            case Accept: {
                request.setStatus(Status.ACCEPTED);
                break;
            }
            case Refinement: {
                request.setNotifyExecutor(false);
                break;
            }
        }
        resourceRequestDAO.updateRequest(request);
        sendMessage(request.getResourcesAdmin(), request.getRegister(), message);
    }

    void sendMessage(User sender, User receiver, Message message) {
    }


    public ResourceRequest getRequestById(long id) {
        return resourceRequestDAO.findById(id);
    }

    public List<ResourceRequest> getResourcesRequest() {
        return resourceRequestDAO.getAllRequests();
    }

    public List<ResourceRequest> getNewResourcesRequest() {

        List<ResourceRequest> requests = getResourcesRequest()
                .stream()
                .filter(request -> request.getStatus().equals(Status.NEW) & request.isNotifyExecutorTrue())
                .collect(Collectors.toList());
        return requests;
    }

    public List<ResourceRequest> getHistoryResourcesRequest() {

        List<ResourceRequest> requests = getResourcesRequest()
                .stream()
                .filter(request -> request.getStatus().equals(Status.ACCEPTED)
                        | request.getStatus().equals(Status.DECLINED))
                .collect(Collectors.toList());
        return requests;
    }

    public ResourceRequest assignResourceAdmin(long requestId, String resourceAdminEmail) {

        ResourceRequest request = resourceRequestDAO.findById(requestId);
        User resourceAdmin = userDAO.findByEmail(resourceAdminEmail);
        request.setUpdate(new Date());
        request.setResourcesAdmin(resourceAdmin);
        resourceRequestDAO.updateRequest(request);
        return request;

    }



}

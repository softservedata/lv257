package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.ResourceRequestDAO;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.ResourceRequest;
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

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        requestService.setStatus(ResourceRequest.Status.NEW);

        Date date = new Date();
        requestService.setUpdate(date);

        User user = userDAO.findByEmail(userSpring.getUsername());

        requestService.setRegister(user);



        resourceRequestDAO.persistRequest(requestService);

    }

    public List<ResourceRequest> getRequestsForRegistrar(){

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDAO.findByEmail(userSpring.getUsername());



        List<ResourceRequest> requests = getResourcesRequest()
                .stream()
                .filter(request -> request.getRegister().getId() == user.getId())
                .collect(Collectors.toList());


        return requests;
    }

    public void response(long requestId, Message message) {
        ResourceRequest request = resourceRequestDAO.findById(requestId);
        request.setUpdate(new Date());
        request.setStatus(message.getRequestStatus());
        System.out.println("before Update");
        resourceRequestDAO.updateRequest(request);
        System.out.println("after Update");

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
        return filterByStatus(getResourcesRequest(), ResourceRequest.Status.NEW);
    }

    public List<ResourceRequest> getHistoryResourcesRequest() {
        List<ResourceRequest> requests = getResourcesRequest();
        List<ResourceRequest> history=filterByStatus(requests, ResourceRequest.Status.ACCEPTED);
        history.addAll(filterByStatus(requests, ResourceRequest.Status.DECLINED));
        return history;
    }

    public ResourceRequest assignResourceAdmin(long requestId, String resourceAdminEmail) {

        ResourceRequest request = resourceRequestDAO.findById(requestId);
        User resourceAdmin = userDAO.findByEmail(resourceAdminEmail);
        request.setUpdate(new Date());
        request.setResourcesAdmin(resourceAdmin);
        resourceRequestDAO.updateRequest(request);
        return request;

    }

    private List<ResourceRequest> filterByStatus(List<ResourceRequest> requests, ResourceRequest.Status status) {
        return requests.stream()
                .filter(request -> request.getStatus().equals(status))
                .collect(Collectors.toList());
    }


}

package com.softserve.edu.resources.serviceImpl;


import com.softserve.edu.resources.daoImpl.ResourceRequestCRUD_DAOImpl;
import com.softserve.edu.resources.daoImpl.ResourceRequestREAD_DAOImpl;
import com.softserve.edu.resources.dto.Message;
import com.softserve.edu.resources.entity.Status;
import com.softserve.edu.resources.entity.ResourceRequest;
import com.softserve.edu.resources.entity.User;
import com.softserve.edu.resources.service.ResourceRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceRequestServiceImpl implements ResourceRequestService {


    @Autowired
    private ResourceRequestCRUD_DAOImpl requestCRUD_DAO;

    @Autowired
    private ResourceRequestREAD_DAOImpl requestREAD_DAO;

    @Override
    public void assignResourceAdmin(ResourceRequest request, User resourceAdmin) {
        request.setResourcesAdmin(resourceAdmin);
        requestCRUD_DAO.updateRequest(request);
    }

    @Override
    public void toRefinement(ResourceRequest request, Message message) {
        request.setUpdate(new Date(new java.util.Date().getTime()));
        request.setNotifyExecutor(false);
        sendMessage(message);
        requestCRUD_DAO.updateRequest(request);
    }

    @Override
    public List<ResourceRequest> showNewRequestsByAdmin(User resourceAdmin) {
        List<ResourceRequest> requestDTOS = getNewResourcesRequest();
        requestDTOS.stream().filter(request -> request.getResourcesAdmin().equals(resourceAdmin))
                .collect(Collectors.toList());
        return requestDTOS;
    }


    @Override
    public void declined(ResourceRequest request, Message message) {
        request.setStatus(Status.DECLINED);
        request.setUpdate(new Date(new java.util.Date().getTime()));
        sendMessage(message);
        requestCRUD_DAO.updateRequest(request);
    }

    @Override
    public void accepted(ResourceRequest request, Message message) {
        request.setStatus(Status.ACCEPTED);
        request.setUpdate(new Date(new java.util.Date().getTime()));
        sendMessage(message);
        requestCRUD_DAO.updateRequest(request);
    }


    private void sendMessage(Message message) {

    }


    @Override
    public List<ResourceRequest> getResourcesRequest() {

        return requestREAD_DAO.getAllRequests();
    }


    //in db
    @Override
    public List<ResourceRequest> getNewResourcesRequest() {
        return requestREAD_DAO.getNewResourcesRequest();
    }

    @Override
    public List<ResourceRequest> getProcessedResourcesRequest() {
        return requestREAD_DAO.getProcessedRequest();
    }

    //on client
    public List<ResourceRequest> getNewResourcesRequest1() {
        List<ResourceRequest> requests = getResourcesRequest();
        requests.stream().filter(request -> request.getStatus().equals(Status.NEW) & request.isNotifyExecutorTrue())
                .collect(Collectors.toList());
        return requests;
    }

    public List<ResourceRequest> getHistoryResourcesRequest1() {
        List<ResourceRequest> requests = getResourcesRequest();
        requests.stream().filter(request -> request.getStatus().equals(Status.ACCEPTED)
                | request.getStatus().equals(Status.DECLINED)).collect(Collectors.toList());
        return requests;
    }


}

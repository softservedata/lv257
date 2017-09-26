package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.impl.ResourceRequestDAOImpl;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestMessageService {
    @Autowired
    private ResourceRequestDAOImpl requestDAO;

    private Message message;
    private Optional<ResourceRequest> resourceRequest;

    public RequestMessageService(Message message) {
        this.message = message;
        this.resourceRequest=requestDAO.findById(message.getId_request());
    }

    public String getSubject(){
        if(!resourceRequest.isPresent()) {
            return new String();
        }
        return "Request for new resource type: " + resourceRequest.get().getResourceType();
    }

    public String getContent() {
        if(!resourceRequest.isPresent()) {
            return new String();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear register,\n");
        stringBuilder.append(message.getPurpose().getMessageContent()+" ");
        stringBuilder.append(message.getComment());
        stringBuilder.append("\n\n"+getSender());
        return stringBuilder.toString();
    }

    public String getReceiver(){
        if(!resourceRequest.isPresent()) {
            return new String();
        }
        return resourceRequest.get().getRegister().getUsername();
    }

    public String getSender(){
        if(!resourceRequest.isPresent()) {
            return new String();
        }
        return resourceRequest.get().getResourcesAdmin().getUsername();
    }

    public RequestMessageService() {
    }
}

package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceRequestDAO;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestMessageService {
    @Autowired
    private ResourceRequestDAO requestDAO;

    private Message message;
    private ResourceRequest resourceRequest;

    public RequestMessageService(Message message) {
        this.message = message;
        this.resourceRequest=requestDAO.findById(message.getId_request());
    }

    public String getSubject(){
        String requestedType=resourceRequest.getResourceType();
        return "Request for new resource type: "+requestedType;
    }

    public String getContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear register,\n");
        stringBuilder.append(message.getPurpose().getMessageContent()+" ");
        stringBuilder.append(message.getComment());
        stringBuilder.append("\n\n"+getSender());
        return stringBuilder.toString();
    }

    public String getReceiver(){
        return resourceRequest.getRegister().getUsername();
    }

    public String getSender(){
        return resourceRequest.getResourcesAdmin().getUsername();
    }

    public RequestMessageService() {
    }
}

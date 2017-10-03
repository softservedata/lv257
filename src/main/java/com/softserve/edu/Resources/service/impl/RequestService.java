package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.impl.DocumentDAOImpl;
import com.softserve.edu.Resources.dao.impl.ResourceRequestDAOImpl;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.Document;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RequestService {

    static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    private final Logger logger;


    @Autowired
    ResourceRequestDAOImpl resourceRequestDAO;
    @Autowired
    DocumentDAOImpl documentDAO;
    @Autowired
    UserDAO userDAO;

    @Autowired
    MailSenderService mailSender;
    @Autowired
    RequestMessageHandler messageHandler;


    public RequestService() {
        logger = LOGGER;
    }


    public void fillUpRequest(ResourceRequest resourceRequest, Document document) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        resourceRequest.setStatus(ResourceRequest.Status.NEW);

        resourceRequest.setDocument(document);

        resourceRequest.setUpdate(new Date());

        User user = userDAO.findByEmail(userSpring.getUsername());

        resourceRequest.setRegister(user);

        resourceRequestDAO.makePersistent(resourceRequest);

    }

    public List<ResourceRequest> getRequestsForRegistrar() {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userDAO.findByEmail(userSpring.getUsername());


        List<ResourceRequest> requests = getResourcesRequest()
                .stream()
                .filter(request -> request.getRegister().getId() == user.getId())
                .collect(Collectors.toList());
        return requests;
    }

    public void response(Message message) {
        Optional<ResourceRequest> requestOptional = resourceRequestDAO.findById(message.getId_request());

        ResourceRequest request;
        if (requestOptional.isPresent()) {
            request = requestOptional.get();
            request.setUpdate(new Date());
            request.setStatus(message.getRequestStatus());
            resourceRequestDAO.makePersistent(request);
            messageHandler.setMessage(message);
            mailSender.sendMessage(messageHandler);
        } else {
            logger.error("ResourseRequest instance with id:" + message.getId_request() + " is undefined.");
        }
    }


    public ResourceRequest getRequestById(long id) {
        Optional<ResourceRequest> request = resourceRequestDAO.findById(id);
        return request.orElse(new ResourceRequest());
    }

    public List<ResourceRequest> getResourcesRequest() {
        return resourceRequestDAO.findAll();
    }


    public List<ResourceRequest> getNewResourcesRequest() {
        return filterByStatus(getResourcesRequest(), ResourceRequest.Status.NEW);

    }

    public List<ResourceRequest> getHistoryResourcesRequest() {
        List<ResourceRequest> requests = getResourcesRequest();
        List<ResourceRequest> history = filterByStatus(requests, ResourceRequest.Status.ACCEPTED);
        history.addAll(filterByStatus(requests, ResourceRequest.Status.DECLINED));
        return history;
    }

    public ResourceRequest assignResourceAdmin(long requestId, String resourceAdminEmail) {

        ResourceRequest request = new ResourceRequest();
        Optional<ResourceRequest> requestOptional = resourceRequestDAO.findById(requestId);
        System.out.println(requestOptional);
        User resourceAdmin = userDAO.findByEmail(resourceAdminEmail);
        if (requestOptional.isPresent()) {
            if (requestOptional.get().getResourcesAdmin() == null) {
                request = requestOptional.get();
                request.setUpdate(new Date());
                request.setResourcesAdmin(resourceAdmin);
                return resourceRequestDAO.makePersistent(request);
            } else {
                logger.error("ResourseRequest instance " + requestOptional.get() + " has already assigned.");
            }
        } else {
            logger.error("ResourseRequest instance with id:" + requestId + " is undefined.");
        }
        return request;

    }

    private List<ResourceRequest> filterByStatus(List<ResourceRequest> requests, ResourceRequest.Status status) {
        return requests.stream()
                .filter(request -> request.getStatus().equals(status))
                .collect(Collectors.toList());
    }

}

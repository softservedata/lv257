package edu.softserve.service;


import edu.softserve.dao.ResourceRequestDAO;
import edu.softserve.dao.UserDAO;
import edu.softserve.entity.ResourceRequest;
import edu.softserve.entity.Status;
import edu.softserve.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class RequestService {

    @Autowired
    ResourceRequestDAO resourceRequestDAO;
    @Autowired
     UserDAO userDAO;

public void fillUpRequest(ResourceRequest requestService){

    org.springframework.security.core.userdetails.User userSpring = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    requestService.setStatus(Status.NEW);

    Date date = new Date();
    requestService.setUpdate(date);

    User user = userDAO.findByEmail(userSpring.getUsername());

    requestService.setRegister(user);

    resourceRequestDAO.persistRequest(requestService);

   }

}

package com.softserve.edu.Resources.controller;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;


import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.service.impl.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Transactional
@RequestMapping("/resources")
public class ResourceRequestAdmController {

    @Autowired
    RequestService requestService;

    @RequestMapping(value = {"/assignRequest"}, method = RequestMethod.POST)
    public @ResponseBody
    String assign(@RequestParam("id") String id
    ) {
        System.out.println(Long.parseLong(id));
        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResourceRequest request = requestService.assignResourceAdmin(Long.parseLong(id), userSpring.getUsername());
        return "success";

    }

//    @RequestMapping(value = {"/sendResponce"}, method = RequestMethod.POST)
//    public @ResponseBody
//    String sendResponse(@RequestParam("id") String id) {
//        System.out.println(Long.parseLong(id));
//        Message m=new Message();
//        m.setComment("sdcf");
//        m.setPurpose(Message.Purpose.Decline);
//        requestService.response(Long.parseLong(id),m);
//        return "success";
//
//    }


    @RequestMapping(value = {"/requests"}, method = RequestMethod.GET)
    public String requests(Model model) {
        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userSpring.getUsername());
        model.addAttribute("resourceAdmin", userSpring.getUsername());
        model.addAttribute("resourceRequest", requestService.getNewResourcesRequest());
        System.out.println(requestService.getNewResourcesRequest());
        return "resourceRequest";
    }

    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String requestsHistory(Model model) {
        model.addAttribute("resourceRequest", requestService.getHistoryResourcesRequest());
        return "resourceRequestHistory";
    }


    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String requestsDetails(@PathVariable int id, Model model) {

        ResourceRequest request = requestService.getRequestById(id);
        model.addAttribute("info", request.getDetails());
       model.addAttribute("file", request.getFile());
        return "resourceRequestDetails";
    }
}

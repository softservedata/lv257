package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.dto.RequestDTO;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.service.impl.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional
@RequestMapping("/resources")
public class ResourceRequestAdmController {

    @Autowired
    RequestService requestService;

    @RequestMapping(value = {"/assignRequest"}, method = RequestMethod.POST)
    public @ResponseBody RequestDTO assign(@RequestParam("id") String id) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ResourceRequest request = requestService.assignResourceAdmin(Long.parseLong(id), userSpring.getUsername());
        RequestDTO requestDTO = new RequestDTO(request);
        return requestDTO;

    }


    @RequestMapping(value = {"/sendResponce"}, method = RequestMethod.POST)
    public @ResponseBody String sendResponse(@RequestBody Message message) {
        requestService.response(message);
        return "success";
    }


    @RequestMapping(value = {"/requests"}, method = RequestMethod.GET)
    public String requests(Model model) {
        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userSpring.getUsername());
        model.addAttribute("resourceAdmin", userSpring.getUsername());
        List<RequestDTO> requestsDTO= requestService.getNewResourcesRequest()
                .stream().map(request->new RequestDTO(request)).collect(Collectors.toList());
        model.addAttribute("resourceRequest",requestsDTO);
        return "resourceRequest";
    }

    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String requestsHistory(Model model) {
        List<RequestDTO> requestsDTO= requestService.getHistoryResourcesRequest()
                .stream().map(request->new RequestDTO(request)).collect(Collectors.toList());
        model.addAttribute("resourceRequest",requestsDTO);
        return "resourceRequestHistory";
    }


//    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
//    public String requestsDetails(@PathVariable int id, Model model) {
//
//        ResourceRequest request = requestService.getRequestById(id);
//        model.addAttribute("request", new RequestDTO(request));
//        return "resourceRequestDetails";
//    }
}
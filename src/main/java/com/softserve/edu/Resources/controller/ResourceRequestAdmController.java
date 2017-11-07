package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.dto.RequestDTO;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.service.impl.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    ResourceTypeService resourceTypeService;

    @RequestMapping(value = {"/assignRequest"}, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> assign(@RequestParam("id") String id) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            ResourceRequest request = requestService.assignResourceAdmin(Long.parseLong(id), userSpring.getUsername());
            RequestDTO requestDTO = new RequestDTO(request);
            return new ResponseEntity<>(requestDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = {"/sendResponce"}, method = RequestMethod.POST)
    public @ResponseBody
    String sendResponse(@RequestBody Message message) {
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
        model.addAttribute("title", "Requests for Resource Type");
        List<RequestDTO> requestsDTO = requestService.getNewResourcesRequest()
                .stream().map(request -> new RequestDTO(request)).collect(Collectors.toList());
        model.addAttribute("resourceRequest", requestsDTO);
        return "resourceRequest";
    }

    @RequestMapping(value = {"/history"}, method = RequestMethod.GET)
    public String requestsHistory(Model model) {
        List<RequestDTO> requestsDTO = requestService.getHistoryResourcesRequest()
                .stream().map(request -> new RequestDTO(request)).collect(Collectors.toList());
        model.addAttribute("resourceRequest", requestsDTO);
        return "resourceRequestHistory";
    }


    @RequestMapping(value = "/acceptRequest/{requestId}/{typeId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void acceptRequest(@PathVariable("requestId") long requestId,
                              @PathVariable("typeId") long typeId) {
      //  resourceTypeService.instantiateType(typeId);
        requestService.acceptRequest(requestId);
    }
}
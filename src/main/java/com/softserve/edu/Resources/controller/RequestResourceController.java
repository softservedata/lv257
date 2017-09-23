package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.service.impl.RequestService;
import com.softserve.edu.Resources.util.FileUploadUtility;
import com.softserve.edu.Resources.validator.UploadFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/resources")
public class RequestResourceController {

    @Autowired
    RequestService requestService;

    private static final Logger logger = LoggerFactory.getLogger(RequestResourceController.class);

    @RequestMapping(value="/request", method= RequestMethod.GET)
    public ModelAndView sendResourcesRequests(@RequestParam(value = "operation", required = false) String operation) {

        requestService.getRequestsForRegistrar();

        ModelAndView mv = new ModelAndView("sendRequest");

        ResourceRequest nRequest = new ResourceRequest();

        mv.addObject("request", nRequest);
        mv.addObject("userClickSendRequest", true);
        mv.addObject("title", "Send Request");

       if(operation != null){
            if(operation.equals("request")){
                mv.addObject("message", "Request sent successfully!");
            }
        }

        return mv;
    }

    @RequestMapping(value="/request", method=RequestMethod.POST)
    public String handleRequestSubmission(@Valid @ModelAttribute("request") ResourceRequest mRequest, BindingResult results,
                                          Model model, HttpServletRequest httpRequest) throws Exception {

        //check if there are any errors
        new UploadFileValidator().validate(mRequest, results);


        if(results.hasErrors()){
            //model.addAttribute("userClickSendRequest", true);
            model.addAttribute("title", "Send Request");
            model.addAttribute("message", "Validation failed for sending request!");

            return "sendRequest";
        }

        requestService.fillUpRequest(mRequest);

        logger.info(mRequest.toString());

        if(!mRequest.getFile().getOriginalFilename().equals("")){
            FileUploadUtility.uploadFile(httpRequest, mRequest.getFile(),mRequest.getCode());
        }

       return "redirect:/resources/request?operation=request";
    }

    @RequestMapping(value={"/story"}, method= RequestMethod.GET)
    public String sendRegistrarRequests(Model model) {

        model.addAttribute("gRequest", requestService.getRequestsForRegistrar());
        model.addAttribute("title", "Story of Request");

        return "requestHistory";
    }


    @RequestMapping(value={"/info/{id}"}, method= RequestMethod.GET)
    public String infoResourcesRequests(@PathVariable int id, Model model) {

        ResourceRequest request = requestService.getRequestById(id);
        model.addAttribute("theme", request.getResourceType());
        model.addAttribute("info", request.getDescription());
        model.addAttribute("code", request.getCode());
        model.addAttribute("title", "Info about Request");

        return "infoRequest";
    }
}

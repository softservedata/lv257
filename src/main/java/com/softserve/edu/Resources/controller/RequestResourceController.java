package edu.softserve.controller;

import edu.softserve.entity.ResourceRequest;
import edu.softserve.service.RequestService;
import edu.softserve.util.FileUploadUtility;
import edu.softserve.validator.UploadFileValidator;
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
                                          Model model, HttpServletRequest httpRequest){

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
}

package edu.softserve.controller;

import edu.softserve.entity.ResourceRequest;
import edu.softserve.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @ResponseBody
    public String handleRequestSubmission(@ModelAttribute("request") ResourceRequest mRequest){

        requestService.fillUpRequest(mRequest);

        logger.info(mRequest.toString());

       return "redirect:/resources/request?operation=request";
    }
}

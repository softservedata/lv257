package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.Document;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.service.impl.DocumentService;
import com.softserve.edu.Resources.service.impl.RequestService;
import com.softserve.edu.Resources.util.FileUploadUtility;
import com.softserve.edu.Resources.validator.UploadFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/resources")
public class RequestResourceController {

    @Autowired
    RequestService requestService;
    @Autowired
    DocumentService documentService;

    private static final Logger logger = LoggerFactory.getLogger(RequestResourceController.class);

    @RequestMapping(value="/request", method= RequestMethod.GET)
    public String sendResourcesRequests(ModelMap mv, @RequestParam(value = "operation", required = false) String operation) {

        ResourceRequest nRequest = new ResourceRequest();
        Document nDocument = new Document();

        mv.addAttribute("mRequest", nRequest);
        mv.addAttribute("document", nDocument);
        mv.addAttribute("userClickSendRequest", true);
        mv.addAttribute("title", "Send Request");

        if(operation != null){
            if(operation.equals("request")){
                mv.addAttribute("message", "Request sent successfully!");
            }
        }

        return "sendRequest";
    }

    @RequestMapping(value="/request", method=RequestMethod.POST)
    public String handleRequestSubmission(@Valid @ModelAttribute("mRequest") ResourceRequest mRequest,
                                          BindingResult requestResults, @ModelAttribute("document") Document document,
                                          BindingResult documentResults, Model model, HttpServletRequest httpRequest)
                                          throws Exception {

        //check if there are any errors
        new UploadFileValidator().validate(document, documentResults);


        if(requestResults.hasErrors() || documentResults.hasErrors()){
            model.addAttribute("userClickSendRequest", true);
            model.addAttribute("title", "Send Request");
            model.addAttribute("message", "Validation failed for sending request!");

            return "sendRequest";
        }

        documentService.fillUpDocument(document);
        requestService.fillUpRequest(mRequest, document);


        logger.info(mRequest.toString());

        if(!document.getFile().getOriginalFilename().equals("")){
            FileUploadUtility.uploadFile(httpRequest, document.getFile(),document.getCode());
        }

        return "redirect:/resources/request?operation=request";
    }

    @RequestMapping(value={"/story"}, method= RequestMethod.GET)
    public String sendRegistrarRequests(Model model) {

        model.addAttribute("gRequest", requestService.getRequestsForRegistrar());
        model.addAttribute("title", "Story of Requests");

        return "requestHistory";
    }


    @RequestMapping(value={"/info/{id}"}, method= RequestMethod.GET)
    public String infoResourcesRequests(@PathVariable int id, Model model) {

        ResourceRequest request = requestService.getRequestById(id);
        model.addAttribute("theme", request.getResourceType());
        model.addAttribute("info", request.getDescription());
        model.addAttribute("code", request.getDocument().getCode());
        model.addAttribute("extension", request.getDocument().getFileExtension());
        model.addAttribute("title", "Info about Request");

        return "infoRequest";
    }
}

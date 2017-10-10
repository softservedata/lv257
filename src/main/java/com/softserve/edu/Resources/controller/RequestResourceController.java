package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.Document;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.service.impl.DocumentService;
import com.softserve.edu.Resources.service.impl.RequestService;
import com.softserve.edu.Resources.util.FileUpload;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.softserve.edu.Resources.entity.ResourceRequest.Status.*;


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
                                          BindingResult documentResults, Model model)
                                          throws Exception {

        //check if there are any errors
        new UploadFileValidator().validate(document, documentResults);

        if(requestResults.hasErrors() || documentResults.hasErrors()){
            model.addAttribute("userClickSendRequest", true);
            model.addAttribute("title", "Send Request");
            model.addAttribute("message", "Validation failed for sending request!");

            return "sendRequest";
        }

        logger.info(mRequest.toString());

        documentService.fillUpDocument(document);
        requestService.fillUpRequest(mRequest, document);

        return "redirect:/resources/request?operation=request";
    }

    @RequestMapping(value={"/story"}, method= RequestMethod.GET)
    public String sendRegistrarRequests(Model model) {

        model.addAttribute("newRequest", requestService.filterByStatus(requestService.getRequestsForRegistrar(), NEW));
        model.addAttribute("acceptedRequest", requestService.filterByStatus(requestService.getRequestsForRegistrar(), ACCEPTED));
        model.addAttribute("declinedRequest", requestService.filterByStatus(requestService.getRequestsForRegistrar(), DECLINED));
        model.addAttribute("refinementRequest", requestService.filterByStatus(requestService.getRequestsForRegistrar(), TO_REFINEMENT));
        model.addAttribute("title", "Story of Requests");

        return "requestHistory";
    }


    @RequestMapping(value={"/info/{id}"}, method= RequestMethod.GET)
    public String infoResourcesRequests(@PathVariable int id, Model model) {

        ResourceRequest request = requestService.getRequestById(id);
        model.addAttribute("theme", request.getResourceType());
        model.addAttribute("info", request.getDescription());
        model.addAttribute("extension", request.getDocument().getFileExtension());
        model.addAttribute("documentURL", request.getDocument().getDocumentsURL());
        model.addAttribute("title", "Info about Request");

        return "infoRequest";
    }

    @RequestMapping(value = {"/spinnerRequest"}, method = RequestMethod.POST)
    public @ResponseBody
    String spinnerFadeOut() {

        return "Spinner Time Out";

    }
}

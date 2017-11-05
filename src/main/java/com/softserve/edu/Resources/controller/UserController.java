package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.*;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import com.softserve.edu.Resources.validator.UploadFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Optional;

@Controller
@Transactional
public class UserController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    MainController mainController;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    //OK
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //code from http://javastudy.ru/spring-mvc/spring-mvc-pattern-prg-postredirectget/
//    http://www.spring-source.ru/articles.php?type=manual&theme=articles&docs=article_10
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileGET0(ModelMap mm, Principal principal)
//    public ModelAndView profileGET0(Model model, Principal principal, @RequestParam(value = "operation", required = false) String operation) {
    {
        System.out.println("UserController line 55");

        //OK

        if (principal == null) {
            mm.addAttribute("message",
                    "Please log in to see this page");
//            mm.setViewName("403");
            return "403";
        } else {
//            UserDetails nRequest = new UserDetails();
            UserProfileDTO userProfileDTO = userProfileService.createUserProfileDTO(principal);
            Avatar nDocument = new Avatar();

            String userName = principal.getName();
            User user = userService.findByEmail(userName);
//            UserDetails userDetailsetails = userDetailsService.getUserDetailsByUserId(user.getId());
//            Optional<UserDetails> details = userDetailsService.getUserDetailsByUserId(user.getId());
//            mm.addAttribute("details", details.isPresent() ? details.get() : new UserDetails());

            userProfileDTO.setGender("F");

            mm.addAttribute("details", userProfileDTO);
            mm.addAttribute("document", nDocument);
            mm.addAttribute("title", "Profile");
            return "profile";
        }
    }

/*    //code from http://javastudy.ru/spring-mvc/spring-mvc-pattern-prg-postredirectget/
//    http://www.spring-source.ru/articles.php?type=manual&theme=articles&docs=article_10
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profileGET0(ModelAndView modelAndView, Principal principal)
//    public ModelAndView profileGET0(Model model, Principal principal, @RequestParam(value = "operation", required = false) String operation) {
    {
        System.out.println("UserController line 55");

        //OK
        if (principal == null) {
            modelAndView.addObject("message",
                    "Please log in to see this page");
            modelAndView.setViewName("403");
            return modelAndView;
        } else {
//            UserDetails nRequest = new UserDetails();
            UserProfileDTO userProfileDTO = userProfileService.createUserProfileDTO(principal);
            Avatar nDocument = new Avatar();

            userProfileDTO.setGender("F");

            modelAndView.addObject("details", userProfileDTO);
            modelAndView.addObject("document", nDocument);
            modelAndView.addObject("title", "Profile");
            return modelAndView;
        }
    }*/

/*    @RequestMapping(value = "/profile", method = RequestMethod.POST)
//    public ModelAndView profilePOST0(@Valid @ModelAttribute("details") UserDetails userDetails,
    public ModelAndView profilePOST0(@Valid @ModelAttribute("details") UserProfileDTO userProfileDTO,
                                     BindingResult bindingResult, ModelAndView modelAndView
//            , UserProfileDTO userProfileDTO
    ) throws Exception {

        modelAndView.addObject("FieldError0", "FieldError0");
        View view = modelAndView.getView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("title", "bindingResult.hasErrors");
            modelAndView.addObject("details", userProfileDTO);
            modelAndView.setViewName("403");
        } else {
            modelAndView.addObject("title", "profile");
            modelAndView.addObject("details", userProfileDTO);
            userProfileService.saveUserProfile(userProfileDTO);
        }
        return modelAndView;
    }*/

       @RequestMapping(value = "/profile", method = RequestMethod.POST)
//    public ModelAndView profilePOST0(@Valid @ModelAttribute("details") UserDetails userDetails,
    public String profilePOST0(@ModelAttribute(value = "details") @Valid UserProfileDTO userProfileDTO, BindingResult bindingResult,
                               @ModelAttribute("document") Avatar document, BindingResult documentResults,
                               Model model
//            , UserProfileDTO userProfileDTO
    )
            throws Exception {

//        View view = modelAndView.getView();

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "bindingResult.hasErrors");
            model.addAttribute("details", userProfileDTO);
            model.addAttribute("403");
        } else {
            model.addAttribute("title", "Profile");
            model.addAttribute("details", userProfileDTO);
            userProfileService.saveUserProfile(userProfileDTO);
        }
        return "profile";
    }


/*
    @RequestMapping(value = "/profileFile", method = RequestMethod.POST)
//    public ModelAndView profilePOST0(@Valid @ModelAttribute("details") UserDetails userDetails,
    public String profilePOST0File(@ModelAttribute(value = "details") @Valid UserProfileDTO userProfileDTO, BindingResult bindingResult,
                               @ModelAttribute("document") Avatar document, BindingResult documentResults,
                               Model model
//            , UserProfileDTO userProfileDTO
    )
            throws Exception {

//        View view = modelAndView.getView();

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "bindingResult.hasErrors");
            model.addAttribute("details", userProfileDTO);
            model.addAttribute("403");
        } else {
            model.addAttribute("title", "Profile");
            model.addAttribute("details", userProfileDTO);
            userProfileService.saveUserProfile(userProfileDTO);
        }
        return "profile";
    }
*/


    @RequestMapping(value = "/profile4040", method = RequestMethod.GET)
    public ModelAndView profileGET4040(Model model, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("message", "Please sign in");
        return modelAndView;
    }

    @RequestMapping(value="/profileAvatar", method= RequestMethod.GET)
    public String sendResourcesRequests(ModelMap mv
           , @RequestParam(value = "operation", required = false) String operation
)
{

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

        return "profileAvatar";
    }

    @RequestMapping(value="/profileAvatar", method=RequestMethod.POST)
    public String handleRequestSubmission(
//            @Valid @ModelAttribute("mRequest") ResourceRequest mRequest,
//                                          BindingResult requestResults,
                                          @ModelAttribute("document") Document document,
                                          BindingResult documentResults, Model model
)
            throws Exception {

        //check if there are any errors
        new UploadFileValidator().validate(document, documentResults);

/*        if(requestResults.hasErrors() || documentResults.hasErrors()){*/
        if(documentResults.hasErrors()){
            model.addAttribute("userClickSendRequest", true);
            model.addAttribute("title", "Send Request");
            model.addAttribute("message", "Validation failed for sending request!");

            return "profileAvatar";
        }

//        logger.info(mRequest.toString());

//        documentService.fillUpDocument(document);
//        requestService.fillUpRequest(mRequest, document);

        return "redirect:profileAvatar";
    }

    public class FileController {

        private final Logger logger = LoggerFactory.getLogger(FileController.class);

        @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
        @ResponseBody
        public String uploadFile(@RequestParam("file") MultipartFile file) {// имена параметров (тут - "file") - из формы JSP.

            String name = null;

            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    name = file.getOriginalFilename();

                    String rootPath = "C:\\path\\";  //try also "C:\path\"
                    File dir = new File(rootPath + File.separator + "loadFiles");

                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();

                    logger.info("uploaded: " + uploadedFile.getAbsolutePath());

                    return "You successfully uploaded file=" + name;

                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + name + " because the file was empty.";
            }
        }

    }
}
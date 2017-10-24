package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.Document;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

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
            userProfileDTO.setGender("F");
//            ModelAndView profile = new ModelAndView("profile");
            modelAndView.addObject("details", userProfileDTO);

            return modelAndView;
        }
    }

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
    public String profilePOST0(@ModelAttribute(value="details") @Valid UserProfileDTO userProfileDTO,
                                     BindingResult bindingResult, ModelAndView modelAndView
//            , UserProfileDTO userProfileDTO
    )
//            throws Exception
    {

//        modelAndView.addObject("FieldError0", "FieldError0");
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
        return "profile";
    }

    @RequestMapping(value = "/profile4040", method = RequestMethod.GET)
    public ModelAndView profileGET4040(Model model, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("message", "Please sign in");
        return modelAndView;
    }

    @RequestMapping(value = "/profileValidation01", method = RequestMethod.GET)
    public ModelAndView profileValidation01(Model model, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("profileValidation01");
        modelAndView.addObject("message", "Please sign in");
        modelAndView.addObject("grade", "A");
        return modelAndView;
    }

}

/*

    @RequestMapping(value="/profile", method=RequestMethod.POST)
    public String profilePOST(@Valid @ModelAttribute ("details") UserProfileDTO userProfileDTO,
                                BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
                                throws Exception {

        model.addAttribute("FieldError0", "FieldError0");

        if (bindingResult.hasErrors()){
            model.addAttribute("FieldError01", "FieldError01");
            return "profile";
        }

        userProfileService.saveUserProfile(userProfileDTO);
        return "redirect:/profile";
    }
*/
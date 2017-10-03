package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
@Transactional
public class UserController {


    @Autowired
    private HttpServletRequest request;
/* todo : is CHANGED UserService userService; TO UserDetailsService userDetailsService*/
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
/**
@version v.01+
 */
/*
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(Model model, Principal principal) {
        String userName = principal.getName();
        User user = userService.findByEmail(userName);
        ModelAndView profile = new ModelAndView("profile");
        Optional<UserDetails> details = userDetailsService.getUserDetailsByUserId(user.getId());
        profile.addObject("details", details.isPresent() ? details.get() : new UserDetails());
        return profile;
    }

    @RequestMapping(value="/profile", method=RequestMethod.POST)
//    public String handleRequestSubmission(@RequestParam UserDetails userDetails*/
/*@Valid @ModelAttribute("profile") UserDetails userDetails, BindingResult results,
//                                          Model model, HttpServletRequest httpRequest*//*
) throws Exception {

*/
/*    public String handleRequestSubmission(@Valid @ModelAttribute("request") ResourceRequest mRequest, BindingResult results,
                                          Model model, HttpServletRequest httpRequest) throws Exception {*//*


    public String handleRequestSubmission(@ModelAttribute ("details") UserDetails userDetails, Model model) throws Exception {

        System.out.println("=======userDetails==============");
        System.out.println(userDetails);
//        model.addAttribute("userDetails", userDetails);
*/
/*        System.out.println(1);
        for (Map.Entry<String, String> x: userDetails.entrySet()) {
            System.out.println(x.getKey() + " " + x.getValue());
        }
        System.out.println(2);*//*


//        model.addAttribute("bank_id","QQQ");

        //check if there are any errors
        //new UploadFileValidator().validate(userDetails, results);

        */
/*if(results.hasErrors()){
            //model.addAttribute("userClickSendRequest", true);
            model.addAttribute("title", "Send Request");
            model.addAttribute("message", "Validation failed for sending request!");
            return "profile";
        }*//*

        */
/*userDetailsService.fillUpRequest(userDetails);
        logger.info(userDetails.toString());*//*

*/
/*
        if(!mRequest.getFile().getOriginalFilename().equals("")){
            FileUploadUtility.uploadFile(httpRequest, mRequest.getFile(),mRequest.getCode());
        }
*//*

//        return "/profile";
        return "redirect:/profile";
//        return "redirect:/profile?operation=userDetails";
    }
*/


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profilePOST(Model model, Principal principal) {
        String userName = principal.getName();
        User user = userService.findByEmail(userName);
        ModelAndView profile = new ModelAndView("profile");
        Optional<UserDetails> details = userDetailsService.getUserDetailsByUserId(user.getId());
        System.out.println(details.get());
        profile.addObject("details", details.isPresent() ? details.get() : new UserDetails());
        return profile;
    }

    @RequestMapping(value="/profile", method=RequestMethod.POST)
    public String profileGET(@ModelAttribute ("details") UserDetails userDetails, BindingResult result, Model model) throws Exception {

        System.out.println("=======userDetails==============");
        System.out.println(userDetails);


//        Optional<UserDetails> details = userDetailsService.setUserDetailsByUserId(user.getId());

        userDetailsService.saveOrUpdate(userDetails);
        return "redirect:/profile";
    }
}

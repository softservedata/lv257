package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.impl.PrivilegeService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@Transactional
public class UserController {

    @Autowired
    private HttpServletRequest request;
    /* todo : is CHANGED UserService userService; TO UserDetailsService userDetailsService*/
    @Autowired
//    UserService userService;
            UserDetailsService userDetailsService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(Model model, Principal principal) {

        /*
        // After user login successfully.
        String userName = principal.getName();
//        UserDetailsService userDetailsService = new UserDetailsService();
//        UserDetails userDetails = userDetailsService.getUserByEmail(userName);
        model.addAttribute("name01", "name01");
        model.addAttribute("name02a", "name02b");

        return "profile";
        */
        ModelAndView profile = new ModelAndView("profile");
        profile.addObject("profile",userDetailsService.getUserById((long)104));
        return profile;
    }
}

/*

    public ModelAndView users () {
        ModelAndView usersModel = new ModelAndView("users");
        usersModel.addObject("users",userService.getAllUsers());
        return usersModel;
    }
 */
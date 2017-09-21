package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserService;
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
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(Model model, Principal principal) {
        String userName = principal.getName();
        User user = userService.findByEmail(userName);
        ModelAndView profile = new ModelAndView("profile");
        UserDetails details = userDetailsService.getUserDetailsByUserId(user.getId());
        profile.addObject("details", details);
        return profile;
    }

}

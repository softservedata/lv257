package edu.softserve.controller;

import edu.softserve.dao.UserDAO;
import edu.softserve.dto.UserDTO;
import edu.softserve.entity.User;
import edu.softserve.entity.UserDetails;
import edu.softserve.service.PrivilegeService;
import edu.softserve.service.RoleService;
import edu.softserve.service.UserDetailsService;
import edu.softserve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
@Transactional
public class UserController {

    @Autowired
    private HttpServletRequest request;
    /* todo CHANGED UserService userService; TO UserDetailsService userDetailsService*/
    @Autowired
//    UserService userService;
    UserDetailsService userDetailsService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
/* todo CHANGED - сюди не заходить
@RequestMapping(value = "/profile", params = {"id"}, method = RequestMethod.GET)
/*

/* todo cloneAndCHANGE userInfo*/
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        // After user login successfully.
        String userName = principal.getName();
        UserDetailsService userDetailsService = new UserDetailsService();
        UserDetails userDetails = userDetailsService.getUserByEmail(userName);

        return "profile";
    }
}
package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profileGET(Model model, Principal principal) {
        ModelAndView profile = new ModelAndView("profile");

        //        don`t erase
//        profile.addObject("details", userProfileDTO.isPresent() ? details.get() : new UserDetails());
//        UserProfileDTO userProfileDTO = UserProfileService;

        UserProfileDTO userProfileDTO = userProfileService.createUserProfileDTO(principal);
        profile.addObject("details", userProfileDTO);

        return profile;
    }

    @RequestMapping(value="/profile", method=RequestMethod.POST)
    public String profilePOST(@ModelAttribute ("details") UserProfileDTO userProfileDTO, BindingResult result, Model model) throws Exception {
        userProfileService.saveUserProfile(userProfileDTO);

        return "redirect:/profile";
    }

}

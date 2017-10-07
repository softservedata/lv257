package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    UserProfileService userProfileService;
//    @Qualifier("UserProfileService")
    @Autowired
    private UserDetailsService userDetailsService;
//    @Qualifier("UserDetailsService")
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
/**
@version v.01+
*/
/*@RequestMapping(value = "/profilePOSTuseUserDetails", method = RequestMethod.GET)
public ModelAndView profilePOSTuseUserDetails(Model model, Principal principal) {
    String userName = principal.getName();
    User user = userService.findByEmail(userName);
    ModelAndView profile = new ModelAndView("profile");
    Optional<UserDetails> details = userDetailsService.getUserDetailsByUserId(user.getId());
    System.out.println(details.get());
    profile.addObject("details", details.isPresent() ? details.get() : new UserDetails());
    return profile;
}*/


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profilePOST(Model model, Principal principal) {
        String userName = principal.getName();
        User user = userService.findByEmail(userName);
        ModelAndView profile = new ModelAndView("profile");
        Optional<UserDetails> details = userDetailsService.getUserDetailsByUserId(user.getId());

        UserProfileDTO userProfileDTO = new UserProfileDTO();
//        userProfileDTO.setFirstName(details.getFirstName());
        userProfileDTO.setFirstName(details.get ().getFirstName());

        /*UserDetails details1 = new UserDetails();
        details1.setFirstName("all right");*/

        System.out.println(details);
//        don`t erase
//        profile.addObject("details", userProfileDTO.isPresent() ? details.get() : new UserDetails());
        profile.addObject("details", userProfileDTO);
        return profile;
    }

    @RequestMapping(value="/profile", method=RequestMethod.POST)
    public String profileGET(@ModelAttribute ("details") UserProfileDTO userProfileDTO, BindingResult result, Model model) throws Exception {

        System.out.println("=======userDetails==============");
        System.out.println(userProfileDTO);

        return "redirect:/profile";
    }
}

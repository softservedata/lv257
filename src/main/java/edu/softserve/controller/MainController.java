package edu.softserve.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import edu.softserve.dao.RoleDAO;
import edu.softserve.dao.UserDAO;
import edu.softserve.dao.impl.PrivilegeDAOImpl;
import edu.softserve.entity.Privilege;
import edu.softserve.entity.Role;
import edu.softserve.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class MainController {

    /*@Autowired
    private UserValidator userValidator;*/

    @Autowired
    PrivilegeDAOImpl privilegeDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Resources");
        model.addAttribute("message", "Welcome to Resources!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model ) {
        return "loginPage";
    }

    /*@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupPage(Model model ) {
        return "signupPage";
    }*/

    @RequestMapping(value = "/signupPage", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "signupPage"; //назва JSP
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users (Model model) {
        return "usersPage";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView roles () {
        ModelAndView rolesModel = new ModelAndView("rolesPage");
        List<Role> list = roleDAO.getAllRoles();
        List<String> names = new ArrayList<>();
        for (Role x : list) {
            names.add(x.getName());
            System.out.println(x.getName());
        }
        rolesModel.addObject("list",names);
        return rolesModel;
    }

    @RequestMapping(value = "/roleInfo", method = RequestMethod.GET)
    public ModelAndView roleInfo () {
        ModelAndView model = new ModelAndView("roleInfo");
        List<Privilege> list = privilegeDAO.getAllPrivileges();
        List<String> names = new ArrayList<>();
        for (Privilege x:list) {
            names.add(x.getName());
        }
        model.addObject("list",names);
        return model;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ModelAndView addRole () {
        ModelAndView model = new ModelAndView("roleInfo");

        model.addObject("list");
        return model;
    }


    @RequestMapping(value = { "/privileges" }, method = RequestMethod.GET)
    public ModelAndView privilegesPage() {
        ModelAndView model = new ModelAndView("privilegesPage");
        //model.addAttribute("title", "Resources");
        //model.addAttribute("message", "Welcome to Privileges page!\n" + privilegeDAO.getAllPrivileges());
        List<Privilege> list = privilegeDAO.getAllPrivileges();
        List<String> names = new ArrayList<>();
        for (Privilege p : list) {
            names.add(p.getName());
        }
        model.addObject("list",names);
        return model;
    }

    @RequestMapping(value = "/pagination", method = RequestMethod.GET)
    public String pagination (Model model) {

        return "viewWithPaginationExample";
    }
    /*@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }*/


    //SuccessfulUserCreation
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        System.out.println("User Name: "+ userName);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("message", "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!");
        } else {
            model.addAttribute("msg",
                    "You do not have permission to access this page!");
        }
        return "403Page";
    }
}
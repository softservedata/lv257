package edu.softserve.controller;

import edu.softserve.dto.UserDTO;
import edu.softserve.entity.ResourceCategory;
import edu.softserve.entity.User;
import edu.softserve.service.PrivilegeService;
import edu.softserve.service.ResourceCategoryService;
import edu.softserve.service.RoleService;
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
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class MainController {

    @Autowired
    ResourceCategoryService categoryService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model, HttpServletRequest request) {
        System.out.println("Checking if ADMIN");
        System.out.println(request.isUserInRole("ROLE_ADMIN"));
        if (request.isUserInRole("ROLE_ADMIN")) {
            System.out.println(request.isUserInRole("ROLE_ADMIN"));
            System.out.println("Yes I am ADMIN");
        }
        System.out.println("Your IP is " + request.getRemoteAddr());
        model.addAttribute("title", "Resources");
        model.addAttribute("message", "Welcome to Resources!");

        ResourceCategory root = new ResourceCategory("root", null, null);
        ResourceCategory branch1 = new ResourceCategory("branch1", root, null);
        ResourceCategory branch2 = new ResourceCategory("branch2", root, null);
        ResourceCategory leaf1_1 = new ResourceCategory("leaf1_1", branch1, null);
        ResourceCategory leaf1_2 = new ResourceCategory("leaf1_2", branch1, null);
        ResourceCategory leaf2_1 = new ResourceCategory("leaf2_1", branch2, null);
        ResourceCategory leaf2_2 = new ResourceCategory("leaf2_2", branch2, null);
        ResourceCategory leaf1_3 = new ResourceCategory("leaf1_3", branch1, null);

        categoryService.addResourceCategory(root);
        categoryService.addResourceCategory(branch1);
        categoryService.addResourceCategory(branch2);
        categoryService.addResourceCategory(leaf1_1);
        categoryService.addResourceCategory(leaf1_2);
        categoryService.addResourceCategory(leaf2_1);
        categoryService.addResourceCategory(leaf2_2);
        categoryService.addResourceCategory(leaf1_3);

        List<ResourceCategory> list = categoryService.findAllResourceCategories();
        for (ResourceCategory cat : list) {
            System.out.println(cat.toString() + " Root: " + cat.getPathToRoot() + " Level: " + cat.getHierarchyLevel());
        }

        return "welcome";
    }

    @RequestMapping(value = { "/about"}, method = RequestMethod.GET)
    public String aboutPage(Model model) {
        model.addAttribute("title", "About");
        return "about";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "adminPage";
    }

    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public String lookupPage(@RequestParam Map<String,String> lookupby) {
        if (lookupby.get("lookupBy") == null || lookupby.get("lookupBy").equals("byType"))
            return "lookupByType";
        else
            return "lookupByOwner";
    }

/*
    @RequestMapping(value = "/lookup", method = RequestMethod.GET)
    public String lookupPage(Model model) {
        return "lookup";
    }
*/

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public String resourcesPage(Model model) {
        return "resources";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model ) {
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "signup"; //назва JSP
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final UserDTO userDTO) {
        System.out.println("new userDTO is:");
        System.out.println(userDTO);

        final User registered = userService.registerNewUserAccount(userDTO);

        System.out.println("new user info is:");
        System.out.println(registered);
        return new ModelAndView("successfulUserCreation", "user", userDTO);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users () {
        ModelAndView usersModel = new ModelAndView("users");
        usersModel.addObject("users",userService.getAllUsers());
        return usersModel;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView roles () {
        ModelAndView rolesModel = new ModelAndView("roles");
        rolesModel.addObject("list",roleService.getAllRoles());
        return rolesModel;
    }

    //rn - roleName
    //TODO its not safe to show our role names in the URL so we need to ...
    @RequestMapping(value = "/roleInfo", params = {"rn"}, method = RequestMethod.GET)
    public ModelAndView roleInfo (@RequestParam Map<String,String> queryUser) {
        String roleName = queryUser.get("rn");
        ModelAndView model = new ModelAndView("roleInfo");
        model.addObject("list",roleService.getRolePrivileges(roleName));
        return model;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ModelAndView addRole () {
        ModelAndView model = new ModelAndView("roleInfo");

        model.addObject("list");
        return model;
    }


    //Returns list of privileges
    @RequestMapping(value = { "/privileges" }, method = RequestMethod.GET)
    public ModelAndView privilegesPage() {
        ModelAndView model = new ModelAndView("privileges");

        model.addObject("list",privilegeService.getAllPrivileges());
        return model;
    }


    //SuccessfulUserCreation
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessful";
    }

    @RequestMapping(value = "/profile", params = {"id"}, method = RequestMethod.GET)
    public ModelAndView profile (@RequestParam Map<String,String> queryUser) {
        String roleName = queryUser.get("id");
        ModelAndView model = new ModelAndView("profile");
        User user = userService.getUserById(Long.parseLong(queryUser.get("id")));
        model.addObject("user", user);
        System.out.println(user);
        System.out.println(user.getUserDetails());
        model.addObject("userDetails", user.getUserDetails());
        return model;
    }
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        return "profile";
    }
    @RequestMapping(value = { "/account"}, method = RequestMethod.GET)
    public String accountPage(Model model) {
        model.addAttribute("title", "Account");
        return "account";
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
        return "403";
    }

    @RequestMapping(value = "/code", params = {"code"}, method = RequestMethod.GET)
    public ModelAndView instaCode (@RequestParam Map<String,String> queryUser) {
        String authCode = queryUser.get("code");
        ModelAndView model = new ModelAndView("profile");
        /*User user = userService.getUserById(Long.parseLong(queryUser.get("id")));
        model.addObject("user", user);*/
        System.out.println(authCode);
        //model.addObject("userDetails", user.getUserDetails());
        return model;
    }

}
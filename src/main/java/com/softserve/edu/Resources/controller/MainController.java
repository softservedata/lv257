package com.softserve.edu.Resources.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.PrivilegeService;
import com.softserve.edu.Resources.service.impl.ResourceCategoryService;
import com.softserve.edu.Resources.service.impl.RoleService;
import com.softserve.edu.Resources.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

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

    @Autowired
    private FormValidator formValidator;

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
        model.addAttribute("newUser", new UserDTO());
        return "signup"; //назва JSP
    }

//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public String signUp(@ModelAttribute("newUser") UserDTO user) {
//        userService.registerNewUserAccount(user);
//        return "successfulUserCreation";
//    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute(value = "newUser") UserDTO  userDTO, BindingResult result) {

        formValidator.validate(userDTO, result);

        if (result.hasErrors()) {
            return "signup";
        }

        userService.registerNewUserAccount(userDTO);
        return "successfulUserCreation";
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
/*
	Profile is moved to UserController.java
	
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
	
	*/
	
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

    public FormValidator getFormValidator() {
        return formValidator;
    }

    public void setFormValidator(FormValidator formValidator) {
        this.formValidator = formValidator;
    }

    @RequestMapping(value = "/manageTypes", method = RequestMethod.GET)
    public ModelAndView manageTypes() {
        ModelAndView model = new ModelAndView("manageTypes");
//        categoryService.findAllResourceCategories().stream().forEach(categoryService::deleteResourceCategory);

        ResourceCategory root = new ResourceCategory("root", null, null);
        ResourceCategory branch1 = new ResourceCategory("branch1", root, null);
        ResourceCategory branch2 = new ResourceCategory("branch2", root, null);
        ResourceCategory leaf1_1 = new ResourceCategory("leaf1_1", branch1, null);
        ResourceCategory leaf1_2 = new ResourceCategory("leaf1_2", branch1, null);
        ResourceCategory leaf2_1 = new ResourceCategory("leaf2_1", branch2, null);
        ResourceCategory leaf2_2 = new ResourceCategory("leaf2_2", branch2, null);
        ResourceCategory leaf1_3 = new ResourceCategory("leaf1_3", branch1, null);
        System.out.println("HASH");
        System.out.println(root.hashCode());
        System.out.println(branch1.hashCode());
        System.out.println(branch2.hashCode());
        System.out.println(leaf1_1.hashCode());
        System.out.println(leaf1_2.hashCode());
        System.out.println(leaf1_3.hashCode());
        System.out.println(leaf2_1.hashCode());
        System.out.println(leaf2_2.hashCode());

        /*ResourceCategory root = new ResourceCategory("root");
        ResourceCategory branch1 = new ResourceCategory("branch1");
        ResourceCategory branch2 = new ResourceCategory("branch2");
        ResourceCategory leaf1_1 = new ResourceCategory("leaf1_1");
        ResourceCategory leaf1_2 = new ResourceCategory("leaf1_2");
        ResourceCategory leaf2_1 = new ResourceCategory("leaf2_1");
        ResourceCategory leaf2_2 = new ResourceCategory("leaf2_2");
        ResourceCategory leaf1_3 = new ResourceCategory("leaf1_3");*/

        root.getChildrenCategories().add(branch1);
        root.getChildrenCategories().add(branch2);
        branch1.getChildrenCategories().add(leaf1_1);
        branch1.getChildrenCategories().add(leaf1_2);
        branch1.getChildrenCategories().add(leaf1_3);
        branch2.getChildrenCategories().add(leaf2_1);
        branch2.getChildrenCategories().add(leaf2_2);

/*        ResourceType type1 = new ResourceType();
        type1.setTypeName("type1").setCategory(leaf2_2);
        Set<ResourceType> rtset = new HashSet<>();
        rtset.add(type1);
        leaf2_2.setResourceTypes(rtset);*/

/*        categoryService.addResourceCategory(root);
        categoryService.addResourceCategory(branch1);
        categoryService.addResourceCategory(branch2);
        categoryService.addResourceCategory(leaf1_1);
        categoryService.addResourceCategory(leaf1_2);
        categoryService.addResourceCategory(leaf2_1);
        categoryService.addResourceCategory(leaf2_2);
        categoryService.addResourceCategory(leaf1_3);*/

        List<ResourceCategory> list = categoryService.findAllResourceCategories();
        for (ResourceCategory cat : list) {
            System.out.println(cat.toString() + " Root: " + cat.getPathToRoot() + " Level: " + cat.getHierarchyLevel());
            System.out.println("Childrens: " + Arrays.asList(cat.getChildrenCategories()));
        }

        ResourceCategory c1 = categoryService.getRoot().get();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(c1);
            System.out.println(jsonInString);
            model.addObject("inputJson", jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/manageTypes", method = RequestMethod.POST)
    public void saveResultsOfManagingTypes(@RequestBody String outputJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<ResourceCategory> list2 = Arrays.asList(mapper.readValue(outputJson, ResourceCategory[].class));
            System.out.println("DESCENDANTS");
//            list2.addAll(categoryService.getDescendants(list2.get(0)));
            for (ResourceCategory cat : list2) {
                categoryService.updateResourceCategory(cat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
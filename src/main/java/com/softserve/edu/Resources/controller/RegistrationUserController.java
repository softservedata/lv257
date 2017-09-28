package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@Transactional
public class RegistrationUserController {

    @Autowired
    UserService userService;

    @Autowired
    private FormValidator formValidator;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("newUser", new UserDTO());
        return "signup"; //назва JSP
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute(value = "newUser") @Valid UserDTO  userDTO, BindingResult result){

        formValidator.validate(userDTO, result);
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(userDTO, result);
        }

        if (registered == null){
            result.rejectValue("email", "user.is.exist");
            return "signup";
        }

        if (result.hasErrors()){
            return "signup";
        }

        return "successfulUserCreation";
    }

    private User createUserAccount(UserDTO userDTO, BindingResult result) {

        User userRegistrated;
        try {
            userRegistrated = userService.registerNewUserAccount(userDTO);
        } catch (UserAlreadyExistException e) {
            return null;
        }
        return userRegistrated;
    }
}

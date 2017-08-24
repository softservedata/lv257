package edu.softserve.controller;

import edu.softserve.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
//@RequestMapping(value = "/signup")
public class RegisterController {

    /*@RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        UserInfo userForm = new UserInfo();
        model.put("userForm", userForm);
        return "signupPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") UserInfo userInfo,
                                      Map<String, Object> model) {



        // implement your own registration logic here...

        // for testing purpose:
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("birth date: " + user.getBirthDate());
        System.out.println("profession: " + user.getProfession());

        return "SuccessfulUserCreation";
    }*/
}

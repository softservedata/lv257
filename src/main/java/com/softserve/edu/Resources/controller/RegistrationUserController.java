package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.VerificationToken;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;
import com.softserve.edu.Resources.registration.OnRegistrationCompleteEvent;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
@Transactional
public class RegistrationUserController {

    @Autowired
    UserService userService;

    @Autowired
    private FormValidator formValidator;

    @Autowired
    private MessageSource messages;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("newUser", new UserDTO());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute(value = "newUser") @Valid UserDTO  userDTO,
                                      BindingResult result, WebRequest request, Model model){

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

        String message = messages.getMessage("message.regSucc", null, request.getLocale());
        model.addAttribute("message", message);

        try {
            String appUrl = env.getProperty("host.appUrl");
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            return "signup";
        }

        return "signuprequest";
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token, @RequestParam("userId") String userId) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        User userById = userService.getUserById(Long.parseLong(userId));

//        //When we have confirmation url but dont have user in db
//        if (userById == null){
//            String message = messages.getMessage("auth.message.userEmpty", null, locale);
//            model.addAttribute("message", message);
//            return "badUser";
//        }

        //When verificationToken is invalid
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            VerificationToken badToken = userById.getVerificationToken();
            userService.deleteVerificationToken(badToken);
            userService.delete(userById);
            return "badUser";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String message = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", message);
            userService.deleteVerificationToken(verificationToken);
            userService.delete(user);
            return "badUser";
        }

        String message = messages.getMessage("message.confirmed", null, locale);
        model.addAttribute("message", message);

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        userService.deleteVerificationToken(verificationToken);
        return "registrationConfirm";
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

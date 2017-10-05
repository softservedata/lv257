package com.softserve.edu.Resources.registration.listener;

import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.registration.OnRegistrationCompleteEvent;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.VelocityMailService;
import com.softserve.edu.Resources.util.RegistrationConfirmMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

//    @Autowired
//    private MessageSource messages;

//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    VelocityMailService velocityMailService;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

//    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
//        final User user = event.getUser();
//        final String token = UUID.randomUUID().toString();
//        userService.createVerificationTokenForUser(user, token);
//
//        final SimpleMailMessage email = constructEmailMessage(event, user, token);
//        mailSender.send(email);
//    }

//    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
//        final String recipientAddress = user.getUsername();
//        final String subject = "Registration Confirmation";
//       // final String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
//       final String confirmationUrl = "http://localhost:8080" + "/registrationConfirm?token=" + token;
//        final String message = messages.getMessage("message.confReg", null, event.getLocale());
//        final SimpleMailMessage email = new SimpleMailMessage();
//
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + " \r\n" + confirmationUrl);
//        email.setFrom(env.getProperty("mail.username"));
//        return email;
//    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);

       velocityMailService.sendConfirmationMail(constructRegistrationConfirmMail(user,token));
    }
    private final RegistrationConfirmMail constructRegistrationConfirmMail(final User user, final String token ) {
        RegistrationConfirmMail mail = new RegistrationConfirmMail(user.getUsername());
        mail.setHost(env.getProperty("host.appUrl"));
        mail.setToken(token);
        return mail;
    }


}

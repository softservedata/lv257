package com.softserve.edu.Resources.registration.listener;

import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.registration.OnRegistrationCompleteEvent;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.VelocityMailService;
import com.softserve.edu.Resources.util.RegistrationConfirmMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    VelocityMailService velocityMailService;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);
        velocityMailService.sendConfirmationMail(constructRegistrationConfirmMail(user,token));
    }
    private final RegistrationConfirmMail constructRegistrationConfirmMail(final User user, final String token ) {
        RegistrationConfirmMail mail = new RegistrationConfirmMail(user.getUsername());
        mail.setFrom(env.getProperty("mail.username"));
        mail.setHost(env.getProperty("host.appUrl"));
        mail.setToken(token);
        return mail;
    }
}

package com.softserve.edu.Resources.service.impl;
import com.softserve.edu.Resources.util.RegistrationConfirmMail;
import com.softserve.edu.Resources.util.ResponceMail;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
public class VelocityMailService {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    VelocityEngine velocityEngine;

    public void sendResponceMail(ResponceMail mail){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getReceiver());
        message.setSubject(mail.getSubject());

        Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("register", mail.getReceiver());
        velocityContext.put("resourceType", mail.getResourceType());
        velocityContext.put("resourceAdmin", mail.getResourceAdmin());
        velocityContext.put("status",mail.getStatus());
        velocityContext.put("comment",mail.getComment());

        StringWriter stringWriter = new StringWriter();

        template.merge(velocityContext, stringWriter);

        message.setText(stringWriter.toString());

        mailSender.send(message);
    }

    public void sendConfirmationMail(RegistrationConfirmMail mail){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail.getReceiver());
        message.setSubject(mail.getSubject());
        message.setFrom(mail.getFrom());

        Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("host",mail.getHost());
        velocityContext.put("token",mail.getToken());
        velocityContext.put("userId",mail.getUserId());

        StringWriter stringWriter = new StringWriter();

        template.merge(velocityContext, stringWriter);

        message.setText(stringWriter.toString());

        mailSender.send(message);
    }
}

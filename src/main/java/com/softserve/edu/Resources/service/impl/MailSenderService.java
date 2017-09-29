package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.service.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender emailSender;


    public void sendMessage(MessageHandler messageHandler) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(messageHandler.getReceiver());
        mailMessage.setSubject(messageHandler.getSubject());
        mailMessage.setText(messageHandler.getContent());
        emailSender.send(mailMessage);
    }
}

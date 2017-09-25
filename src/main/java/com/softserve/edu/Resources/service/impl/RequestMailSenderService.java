package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


public class RequestMailSenderService {

    private JavaMailSender emailSender=new JavaMailSenderImpl();


    public void sendMessage(Message message) {
        RequestMessageService  messageHandler = new RequestMessageService(message);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(messageHandler.getReceiver());
        mailMessage.setFrom("projectresources257@gmail.com");
        mailMessage.setSubject(messageHandler.getSubject());
        mailMessage.setText(messageHandler.getContent());
        emailSender.send(mailMessage);
    }
}

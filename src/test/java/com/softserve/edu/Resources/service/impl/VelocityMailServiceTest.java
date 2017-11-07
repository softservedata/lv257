package com.softserve.edu.Resources.service.impl;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.softserve.edu.Resources.config.ApplicationConfig;
import com.softserve.edu.Resources.dto.Message;
import com.softserve.edu.Resources.entity.ResourceRequest;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.util.RegistrationConfirmMail;
import com.softserve.edu.Resources.util.ResponceMail;
import org.apache.velocity.app.VelocityEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:mail.properties")
public class VelocityMailServiceTest {

    private static ResourceRequest request;
    private static User resourceAdmin;
    private static User register;
    private static Message messageDto;

    @BeforeClass
    public static void SetUp() {
        messageDto = new Message(0, Message.Purpose.Decline, "Test comment");
        resourceAdmin = new User();
        register = new User();
        register.setUsername("Register@gmail.com");
        resourceAdmin.setUsername("ResourceAdmin@gmail.com");


        request = new ResourceRequest().setResourceName("TestType")
                .setStatus(ResourceRequest.Status.ACCEPTED).setRegister(register).setResourcesAdmin(resourceAdmin);
    }

    @Configuration
    @ComponentScan(
           useDefaultFilters = false,
            includeFilters = {
                    @ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = VelocityMailService.class)
            })
    public static class ContextConfiguration {
        @Autowired
        private Environment env;

        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("localhost");
            mailSender.setPort(3025);
            mailSender.setUsername(env.getProperty("mail.username"));
            mailSender.setPassword(env.getProperty("mail.password"));

            Properties props = mailSender.getJavaMailProperties();

            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
            props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
            props.put("mail.debug", env.getProperty("mail.debug"));

            return mailSender;
        }

        @Bean
        public VelocityEngine getVelocityEngine() {

            VelocityEngine velocityEngine = new VelocityEngine();
            velocityEngine.setProperty("resource.loader", env.getProperty("velocity.resource.loader"));
            velocityEngine.setProperty("class.resource.loader.class", env.getProperty("velocity.class.resource.loader.class"));
            velocityEngine.init();
            return velocityEngine;
        }
    }

    @Autowired
    private VelocityMailService velocityMailService;

    private GreenMail smtpServer;


    @Before
    public void setUp() throws Exception {

        smtpServer = new GreenMail(new ServerSetup(3025, null, "smtp"));
        smtpServer.start();
    }


    @Test
    public void sendResponceMailTest() throws Exception {
        ResponceMail mail = new ResponceMail(messageDto, request);
        String subject = mail.getSubject();

        String content = "Dear Register@gmail.com,\r\n" +
                "Request for a new resource type TestType was processed by ResourceAdmin@gmail.com.\r\n" +
                "Status:accepted.\r\n" +
                "Test comment\r\n" +
                "\r\n" +
                "Thanks.\r\n";


        velocityMailService.sendResponceMail(mail);
        assertReceivedMessageContains(content, subject);
    }

    private void assertReceivedMessageContains(String expectedContent, String expectedSubject) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[0].getContent();
        String subject = receivedMessages[0].getSubject();
        assertEquals(content, expectedContent);
        assertTrue(subject.contains(expectedSubject));

    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }
}

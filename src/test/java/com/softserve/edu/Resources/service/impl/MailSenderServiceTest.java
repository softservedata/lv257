package com.softserve.edu.Resources.service.impl;

import org.junit.Before;
import org.junit.Test;
//import org.jvnet.mock_javamail.Mailbox;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import javax.activation.DataHandler;
import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.jvnet.mock_javamail.Mailbox.*;
import static org.powermock.api.mockito.PowerMockito.when;

public class MailSenderServiceTest {
    @Mock
    JavaMailSender emailSender;

//    @Mock
//    RequestMessageHandler messageHandler;
//
//    @InjectMocks
//    MailSenderService senderService;


  //  @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void sendMessageTest() throws MessagingException, IOException {
//        String subject = "testSubject";
//        String content = "testContent";
//        String mail = "test@gmail.com";
//
//
//        when(messageHandler.getSubject()).thenReturn(subject);
//        when(messageHandler.getContent()).thenReturn(content);
//        when(messageHandler.getReceiver()).thenReturn(mail);
//
//        senderService.sendMessage(messageHandler);
//
//        List<Message> inbox = get(mail);
//
//        assertTrue(inbox.size() == 1);
//        assertEquals(subject, inbox.get(0).getSubject());
//        assertEquals(content, inbox.get(0).getContent());
//
//    }
}

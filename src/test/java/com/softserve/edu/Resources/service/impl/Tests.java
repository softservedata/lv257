package com.softserve.edu.Resources.service.impl;




       // import com.dimitrisli.springEmailTest.config.AppConfig;
//        import com.icegreen.greenmail.util.GreenMail;
//        import com.icegreen.greenmail.util.GreenMailUtil;
//        import com.icegreen.greenmail.util.ServerSetupTest;
        import com.softserve.edu.Resources.config.ApplicationConfig;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.Mockito;
        import org.mockito.MockitoAnnotations;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.mail.javamail.JavaMailSenderImpl;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

        import javax.annotation.Resource;
        import javax.mail.Message;
        import javax.mail.MessagingException;

        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class Tests {

//   @Autowired
//    private JavaMailSenderImpl emailSender;
//
//    private GreenMail testSmtp;
//
//    @Before
//    public void testSmtpInit(){
//        MockitoAnnotations.initMocks(this);
//
//    }
//
//    @Test
//    public void testEmail() throws InterruptedException, MessagingException {
//        testSmtp = new GreenMail(ServerSetupTest.SMTP);
//        testSmtp.start();
//        //don't forget to set the test port!
//        emailSender.setPort(3025);
//        emailSender.setHost("localhost");
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("test@sender.com");
//        message.setTo("test@receiver.com");
//        message.setSubject("test subject");
//        message.setText("test message");
//        emailSender.send(message);
//
//        Message[] messages = testSmtp.getReceivedMessages();
//        assertEquals(1, messages.length);
//        assertEquals("test subject", messages[0].getSubject());
//        String body = GreenMailUtil.getBody(messages[0]).replaceAll("=\r?\n", "");
//        assertEquals("test message", body);
//    }
//
//    @After
//    public void cleanup(){
//        testSmtp.stop();
//    }
}

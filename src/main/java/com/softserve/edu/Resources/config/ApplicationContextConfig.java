package com.softserve.edu.Resources.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@ComponentScan("com.softserve.edu.*")
@EnableTransactionManagement
/*// Load to Environment.
@PropertySource("classpath:datasource-cfg.properties")*/
public class ApplicationContextConfig {


//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
//        // Load property in message/validator.properties
//        rb.setBasenames(new String[] { "messages/validator" });
//        return rb;
//    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("/WEB-INF/messages");
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver createResolver() {
        return   new StandardServletMultipartResolver();


    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("utf-8");
//        return resolver;
//    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");

        mailSender.setPort(587);

        mailSender.setUsername("projectresources257@gmail.com");
        mailSender.setPassword("lv257resources");

        Properties props = mailSender.getJavaMailProperties();


        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
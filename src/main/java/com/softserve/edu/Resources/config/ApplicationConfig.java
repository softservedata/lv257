package com.softserve.edu.Resources.config;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


import java.io.IOException;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@Import(DBConfig.class)
@ComponentScan(basePackages = {"com.softserve.edu.Resources"})
@PropertySource("classpath:mail.properties")
public class ApplicationConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver createResolver() {
        return new StandardServletMultipartResolver();
    }


    @Autowired
    private Environment env;

    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(env.getProperty("mail.host"));
        mailSender.setPort(env.getProperty("mail.port", Integer.class));
        mailSender.setUsername(env.getProperty("mail.username"));
        mailSender.setPassword(env.getProperty("mail.password"));

        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.debug", env.getProperty("mail.debug"));

        return mailSender;
    }

    @Bean
    public VelocityEngine getVelocityEngine(){

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("resource.loader", env.getProperty("velocity.resource.loader"));
        velocityEngine.setProperty("class.resource.loader.class", env.getProperty("velocity.class.resource.loader.class"));
        velocityEngine.init();
        return velocityEngine;
    }

}
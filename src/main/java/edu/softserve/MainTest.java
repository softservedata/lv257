package edu.softserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import edu.softserve.config.ApplicationContextConfig;
import edu.softserve.config.DBConfig;
import edu.softserve.config.WebMvcConfig;
import edu.softserve.config.WebSecurityConfig;
import edu.softserve.service.ResourceTypeService;
import edu.softserve.serviceImpl.ResourceTypeServiceImpl;
import edu.softserve.serviceImpl.TestService;

public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationContextConfig.class);
        ctx.refresh();

        TestService testService = ctx.getBean(TestService.class);
        testService.test();

        ResourceTypeService resService = ctx.getBean(ResourceTypeService.class);
        resService.testHello();
        
        
        

    }

}

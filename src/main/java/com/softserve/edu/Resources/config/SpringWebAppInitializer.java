package com.softserve.edu.Resources.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static String location = "/resources/upload";
    private static final int maxFileSize = 5 * 1024 * 1024; // 5 MB


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { ApplicationConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(

                new MultipartConfigElement(location, maxFileSize, maxFileSize * 2, maxFileSize / 2)
        );
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {


        location = ((File) servletContext.getAttribute("javax.servlet.context.tempdir")).getAbsolutePath();
        super.onStartup(servletContext);

        servletContext.addListener(new SessionListener());
        //31.08.2017
        //TODO after first initialization to fulfill tables in DB with testing data(privileges, roles, users, resources etc )

    }


}
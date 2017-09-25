package com.softserve.edu.Resources.config;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.io.File;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String LOCATION = "/tmp/resources/uploads";
    private static final int maxFileSize = 5 * 1024 * 1024; // 5 MB


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
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
                new MultipartConfigElement(LOCATION, maxFileSize, maxFileSize * 2, maxFileSize / 2)
        );
    }

    /*@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
                new DispatcherServlet(appContext));
        String location = ((File) servletContext.getAttribute("javax.servlet.context.tempdir")).getAbsolutePath();
        MultipartConfigElement multipartConfigElement
                = new MultipartConfigElement(location, maxFileSize, maxFileSize * 2, maxFileSize / 2);
        dispatcher.setMultipartConfig(multipartConfigElement);

        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(appContext);

        servletContext.addListener(contextLoaderListener);

        // Filter.
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);

        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");

        //31.08.2017
        //TODO after first initialization to fulfill tables in DB with testing data(privileges, roles, users, resources etc )

    }*/


}
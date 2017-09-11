package com.softserve.edu.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.softserve.edu.ReporterWrapper;

public class AppTest {
    public static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void testApp() {
        logger.error("AppTest Error");
        logger.warn("AppTest Warning");
        logger.info("AppTest Info");
        logger.debug("AppTest Debug");
        logger.trace("AppTest Trace");
        Reporter.log("AppTest: Reporter.log",3,true);
        ReporterWrapper.get().error("AppTest: Reporter.log message");
        System.out.println("public void testApp() done");
        System.out.println("surefire.reports.directory = " 
                + System.getProperty("surefire.reports.directory"));
        Assert.assertTrue(true);
    }

}

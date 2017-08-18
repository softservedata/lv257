package com.softserve.edu.resources;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

    @Test
    public void testApp() {
        System.out.println("public void testApp() done");
        System.out.println("surefire.reports.directory = " 
                + System.getProperty("surefire.reports.directory"));
        Assert.assertTrue(true);
    }

}

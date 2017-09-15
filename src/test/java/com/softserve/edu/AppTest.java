package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {
    
    @Test
    public void testApp() {
        System.out.println("surefire.reports.directory = "
                + System.getProperty("surefire.reports.directory"));
        Assert.assertTrue(true);
    }

}

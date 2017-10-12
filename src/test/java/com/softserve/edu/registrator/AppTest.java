package com.softserve.edu.registrator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {
    
    @Test
    public void testApp() {
        System.out.println("surefire.reports.directory = " 
                + System.getProperty("surefire.reports.directory"));
        //
        System.out.println("\nUsed Memory = " 
                + (Runtime.getRuntime().totalMemory() 
                        - Runtime.getRuntime().freeMemory()) / (1024.0 * 1024.0) + "Mb");
        System.out.println("\nTotal Amount of Memory = " 
                + Runtime.getRuntime().totalMemory() / (1024.0 * 1024.0) + "Mb");
        System.out.println("\nMaximum Amount of Memory = " 
                + Runtime.getRuntime().maxMemory() / (1024.0 * 1024.0) + "Mb");
        //
        System.out.println("Test done");
        //Assert.assertTrue(true);
        //Assert.assertTrue(false);
        Assert.assertTrue(1>2);
    }
    
}

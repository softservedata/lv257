package com.softserve.edu.algorithmTasks;

import org.junit.Assert;
import org.junit.Test;

public class PascalsTriangleTest {

    PascalsTriangle pt = new PascalsTriangle();

    @Test
    public void testGetNsRowsOfPascalsTriangle1() {

        int[][] arrayExpected = {
                {1},
                {1,1},
                {1,2,1},
        };

        int[][] arrayActual = pt.getNsRowsOfPascalsTriangle(3);
        Assert.assertArrayEquals(arrayExpected, arrayActual);

    }

    @Test
    public void testGetNsRowsOfPascalsTriangle2() {

        int[][] arrayExpected = {
                {1},
                {1,1},
                {1,2,1},
                {1,3,3,1},
                {1,4,6,4,1},
        };
        
        int[][] arrayActual = pt.getNsRowsOfPascalsTriangle(5);
        Assert.assertArrayEquals(arrayExpected, arrayActual);

    }
    
}

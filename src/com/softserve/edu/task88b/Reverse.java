package com.softserve.edu.task88b;


public class Reverse {

    public void revers(int n){

        int result;
        while (n > 0) {
            result = n%10;
            n = n/10;
            System.out.print(result);
        }
    }
}

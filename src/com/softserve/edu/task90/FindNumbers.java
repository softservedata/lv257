package com.softserve.edu.task90;

import java.util.ArrayList;
import java.util.List;

public class FindNumbers {

    private final int MAX_INTEGER = 200000000;
    private int p;
    private int q;
//    private List<Integer> dividersOfI = new ArrayList<>();
//    private List<Integer> dividersOfJ = new ArrayList<>();

    public void findNumbers(int m, int n){

        List<Integer> dividersOfI = new ArrayList<>();
        List<Integer> dividersOfJ = new ArrayList<>();

        for (int i = 1; i <= MAX_INTEGER; i++){
            for (int j = 1; j <= MAX_INTEGER; j++){
                int coef1 = (m * i);
                int coef2 = (n * j);
                if (coef1 == coef2){
                    dividersOfI = getDividers(i);
                    dividersOfJ = getDividers(j);
                    boolean flag = checkDividersOfIAndJ(dividersOfI, dividersOfJ);
                    if (flag){
                        p = j;
                        q = i;
                        System.out.println("p = " + p + "; q = " + q);
                        return;
                    }
                }
            }
        }
    }

    public boolean checkDividersOfIAndJ(List<Integer> dividersOfI, List<Integer> dividersOfJ) {

        int dividerI;
        int dividerJ;
        for (int k = 0; k < dividersOfI.size(); k++){

            dividerI = dividersOfI.get(k);
            for (int l = 0; l < dividersOfJ.size(); l++){

                dividerJ = dividersOfJ.get(l);
                if (dividerI == dividerJ){
                    return false;
                }
            }
        }
        return true;
    }

    public List<Integer> getDividers(int k) {

        List<Integer> dividers = new ArrayList<>();
        for (int i =2; i < k; i++){

            if (k % i == 0){
                dividers.add(i);
            }
        }
        return dividers;
    }
}

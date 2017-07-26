package com.softserve.edu.task330;


import java.util.ArrayList;
import java.util.List;

public class PerfectNumber {

    private List<Integer> result = new ArrayList<>();

    public List<Integer> getPerfectNumbers(int n){

        for (int i = 1; i < n; i++){

            List<Integer> dividers = getDividers(i);
            int sum = 0;
            for (int j = 0; j < dividers.size(); j++){
                sum += dividers.get(j);
            }
            if (sum == i) result.add(i);
        }
        return result;
    }

    public List<Integer> getDividers(int k) {

        List<Integer> dividers = new ArrayList<>();
        for (int i =1; i < k; i++){

            if (k % i == 0){
                dividers.add(i);
            }
        }
        return dividers;
    }

}

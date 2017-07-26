package com.softserve.edu.task330;

import java.util.List;

/**
 * Натуральное число называется совершенным, если оно
 равно сумме всех своих делителей, за исключением себя самого. Число
 6 – совершенное, так как 6 = 1+2+3. Число 8 – не совершенное, так как
 8 ≠ 1+2+4.Дано натуральное число n. Получить все совершенные числа,
 меньшие n.
 */
public class Solution {

    public static void main(String[] args) {

        PerfectNumber perfectNumber = new PerfectNumber();
        List<Integer> numbers = perfectNumber.getPerfectNumbers(20000);
        for (Integer integer : numbers){
            System.out.print(integer + " - ");
        }
    }
}

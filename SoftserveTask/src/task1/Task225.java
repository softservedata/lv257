package task1;

import java.util.ArrayList;
import java.util.List;

public class Task225 {
    private Task225() {
    }

    /**
     * Find all numbers for which N is divided by number in power 2 but not in power 3:
     *
     * @param number natural N
     * @return list of all found special dividers
     */
    public static List<Integer> getSpecialDivider(int number) {
        List<Integer> resultNumbers = new ArrayList<>();
        try {
            if (number <= 0) throw new IllegalArgumentException("number " + number + " isn*t Natural");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return resultNumbers;
        }

        int divider = 1;
        do {
            if ((number % Math.pow(divider, 2) == 0) && (number % Math.pow(divider, 3) != 0)) {
                resultNumbers.add(divider);
            }
            divider++;
        } while (Math.pow(divider, 2) <= number);
        return resultNumbers;
    }
}

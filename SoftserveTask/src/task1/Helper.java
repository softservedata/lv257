package task1;

class Helper {
    private Helper() {
    }

    static int sumOfDigitInPow(int number, int power) {
        int sum = 0;
        while (number != 0) {
            sum += Math.pow(number % 10, power);
            number = number / 10;
        }
        return sum;
    }

    static int numberOfDigit(int number) {
        int count = 0;
        while (number != 0) {
            number = number / 10;
            count++;
        }
        return count;
    }
}

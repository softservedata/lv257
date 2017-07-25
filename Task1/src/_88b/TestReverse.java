package _88b;

/**
 * Поменять порядок цифр числа n на обратный.
 */
public class TestReverse {

    public static void main(String[] args) {

        Reverse reverse = new Reverse();
        int revers = reverse.revers(123456);
        System.out.println(revers);
        reverse.printRevers(123456);

    }
}

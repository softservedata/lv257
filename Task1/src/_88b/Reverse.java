package _88b;


public class Reverse {

    private StringBuilder builder = new StringBuilder();

    public int revers(int n){

        int result = 0;
        if (n == 0) return result;
        while (n > 0) {
            result = n%10;
            n = n/10;
            builder.append(result);
        }
        result = Integer.parseInt(String.valueOf(builder));
        return result;
    }

    public void printRevers(int n){

        int result;
        if (n == 0) System.out.print(0);
        while (n > 0) {
            result = n%10;
            n = n/10;
            System.out.print(result);
        }
    }
}

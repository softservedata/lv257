package _88b;


public class Reverse {

    private StringBuilder builder = new StringBuilder();


    public void revers(int n){

        int result;
        while (n > 0) {
            result = n%10;
            n = n/10;
            System.out.print(result);
        }
    }
}

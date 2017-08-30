package edu.softserve;

public class A {
    public static void main(String[] args) {
        new A().method( new Mammal());
    }

    void method (Animal a)
    {
        System.out.println("Success");
    }
}

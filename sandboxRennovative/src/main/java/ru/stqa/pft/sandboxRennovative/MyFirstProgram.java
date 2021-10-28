package ru.stqa.pft.sandboxRennovative;


public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("me");
        hello("user");
        Square s = new Square(5);
        System.out.println("Square - kvadrat " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Square pryamoyg: -- " + r.a + " and " + r.b + " = " + r.area());
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}


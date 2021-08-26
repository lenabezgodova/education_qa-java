import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MyFirstProgram {

    public static void main(String[] args){
        System.out.println("Hello world");


        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 2);

        System.out.println("Distance between two coordinates: " +  distance(p1, p2));
    }


    public static double distance(Point p1, Point p2){
        return sqrt(pow((p2.coorX - p1.coorX),2) + pow((p2.coorY - p1.coorY), 2));

    }
}





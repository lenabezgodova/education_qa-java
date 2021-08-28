package ru.qa.test.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {

    double coorX;
    double coorY;

    public Point(double coorX, double coorY){
        this.coorX = coorX;
        this.coorY = coorY;
    }

        public double distance(Point p2){
        return sqrt(pow((p2.coorX - this.coorX),2) + pow((p2.coorY - this.coorY), 2));

    }

}

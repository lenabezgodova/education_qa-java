package ru.qa.test.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestDistance {

    @Test
    public void verification(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 2);

        Assert.assertEquals(p1.distance(p2), 2.8284271247461903);


        assert p1.distance(p2) == 2.8284271247461903;
    }

    @Test
    public void verificationZeroValue(){
        var p1 = new Point(2, 2);
        Point p2 = new Point(2, 2);

        Assert.assertEquals(p1.distance(p2), 0);

    }




}
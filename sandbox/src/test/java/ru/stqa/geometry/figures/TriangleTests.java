package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test

    void canCalculatePerimeter(){
        var d = new Triangle(5, 5, 7);
        double result = d.TrianglePerimeter();
        Assertions.assertEquals(17, result);
    }

    @Test

    void canCalculateSquare2(){
        var e = new Triangle(5, 5, 7);
        double result = e.TriangleSquare2();
        Assertions.assertEquals(8.48528137423857, result);
    }
    @Test

     void cannotCreateTriangleWithNegativeSideAndUnequaleSides() {
        try {
            new Triangle(10, 1, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }

    @Test
    void testEquality() {
        var r1 = new Triangle(3, 4, 6);
        var r2 = new Triangle(6, 3, 4);
        Assertions.assertEquals(r1, r2);
    }
}


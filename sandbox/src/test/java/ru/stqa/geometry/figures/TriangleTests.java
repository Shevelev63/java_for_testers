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
}

package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test

    void canCalculatePerimeter(){
        Assertions.assertEquals(17, Triangle.TrianglePerimeter(5, 5, 7));
    }

    @Test

    void canCalculateSquare2(){
        Assertions.assertEquals(8.48528137423857, Triangle.TriangleSquare2(5,5,7));
    }
}

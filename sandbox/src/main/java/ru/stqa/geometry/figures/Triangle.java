package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle (int a, int b, int c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Одна из сторон треугольника отрицательна");
        }
        if (a >= b + c || b >= a + c || c >= a + b) {
            throw new IllegalArgumentException("Наррушено неравенство треугольника");
        }
    }

    public static void main(String[] args) {
        printTriangleArea(new Triangle(5, 5, 7));
        printTriangleSquare(new Triangle(5, 5, 7));

    }

    public static void printTriangleArea(Triangle t) {
        System.out.println("Периметр треугольника = " + t.TrianglePerimeter());

    }

    public static void printTriangleSquare(Triangle t){
        System.out.println("Площадь треугольника = " + t.TriangleSquare2());
    }

    public double TrianglePerimeter() {
        return this.a + this.b + this.c;
    }

    public double TriangleSquare2() {
        double s= (a+b+c)/2;
        return Math.sqrt(s * (s - this.a) * (s - this.b) * (s - this.c));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (this.a == triangle.a || this.a == triangle.b || this.a == triangle.c)
                || (this.b == triangle.a || this.b == triangle.c || this.b == triangle.b)
                || (this.c == triangle.a || this.c == triangle.b || this.c == triangle.c);

    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}




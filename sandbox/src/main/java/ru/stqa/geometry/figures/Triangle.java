package ru.stqa.geometry.figures;

public class Triangle {
    public static void main(String[] args) {
        printTriangleArea(5 , 5, 7 );
        printTriangleSquare(5 , 5, 7);

    }
    public static int TrianglePerimeter(int a, int b, int c) {
        return a + b + c;
    }

    public static void printTriangleArea(int a, int b, int c) {
        System.out.println("Периметр треугольника = " + TrianglePerimeter(a, b, c));

    }

    public static void printTriangleSquare(int a, int b, int c){
        System.out.println("Площадь треугольника = " + TriangleSquare2(a, b, c));
    }

    public static double TriangleSquare2(int a, int b, int c) {
        double s= (a+b+c)/2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

}



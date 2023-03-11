package ru.croc.task1;

import java.util.Scanner;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {

        Point[] points = inputPointsData();
        Triangle triangle = new Triangle(points[0], points[1], points[2]);

        if (triangle.isTriangle()) {
            System.out.printf("Площадь треугольника: %.1f %n", triangle.area());
        } else {
            System.out.println("Это не треугольник");
        }
    }

    public static Point[] inputPointsData() {
        Scanner scan = new Scanner(System.in);
        Point[] points = new Point[3];
        char[] coord_name = new char[] {'X','Y'};
        for (int i = 0; i < 3; i++) {
            double[] coords = new double[2];
            for (int j = 0; j < 2; j++) {
                System.out.print("Введите координату " + coord_name[j] + " вершины " + (i + 1) + ": ");
                coords[j] = scan.nextDouble();
            }
            points[i] = new Point(coords[0], coords[1]);
        }
        return points;
    }

    public static class Triangle {
        Point p1, p2, p3;
        public Triangle( Point p1, Point p2, Point p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
        public boolean isTriangle() {
            if ((this.p1.x - this.p2.x)*(this.p3.y - this.p2.y) == (this.p1.y - this.p2.y)*(this.p3.x - this.p2.x))
            // если все 3 точки лежат на одной прямой (если точки повторяются, то через них тем более можно провести одну прямую)
            { return false; }
            return true;
        }
        public double sideLength(Point p1, Point p2) {
            return sqrt(pow(p1.x - p2.x, 2)+pow(p1.y - p2.y, 2));
        }
        public double area() {
            double aSide = sideLength(this.p1, this.p2); // вот тут бы больше snake_case подошел: a_side
            double bSide = sideLength(this.p2, this.p3);
            double cSide = sideLength(this.p3, this.p1);

            double Perimeter = aSide + bSide + cSide;
            double p = Perimeter/2;

            return sqrt(p*(p-aSide)*(p-bSide)*(p-cSide)); // формула Герона
        }
    }
    public static class Point {
        double x;
        double y;
        public Point(double x,double y) {
            this.x = x;
            this.y = y;
        }
    }
}
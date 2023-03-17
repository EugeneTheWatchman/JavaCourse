package ru.croc.task4;

public class Circle extends Figure {
    int radius;
    Point center;

    public Circle(Point centralPoint, int radius) {
        this.center = centralPoint;
        this.radius = radius;
    }

    public Circle() {
        this(new Point(0,0),0);
    }
}

package ru.croc.task5;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class Circle extends Figure {
    private int radius;
    private Point center;

    public Circle(Point centralPoint, int radius) {
        this.center = centralPoint;
        this.radius = radius;
    }

    public Circle() {
        this(new Point(0, 0), 0);
    }

    public Point getCenter() {
        return center.clone();
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public Circle clone() {
        return new Circle(this.center, this.radius);
    }

    @Override
    public Boolean contains(Point point) {
        return this.radius >= sqrt( pow(this.center.x - point.x, 2) + pow(this.center.y - point.y, 2) );
    }

    @Override
    public void move(int dx, int dy) {
        this.center.move(dx, dy);
    }
}

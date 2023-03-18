package ru.croc.task5;

public class Rectangle extends Figure{
    private Point lowerLeftCorner;
    private Point topRightCorner;

    public Rectangle(Point lowerLeftCorner, Point topRightCorner) throws Exception {
        // исходя из задания, делаю предположение что начало координат располагается слева снизу (что необычно для работы с изоражениями)
        // вывод сделал из:
        // 1) "Прямоугольники определяются координатами левого нижнего и правого верхнего углов"
        // 2) "R (100, 100), (150, 200): Car"

        if (lowerLeftCorner.x >= topRightCorner.x) {
            throw new Exception("Координата Х левого нижнего угла должна быть меньше правого верхнего");
        }
        if (lowerLeftCorner.y >= topRightCorner.y) {
            throw new Exception("Координата Y левого нижнего угла должна быть меньше правого верхнего");
        }

        this.lowerLeftCorner = lowerLeftCorner;
        this.topRightCorner = topRightCorner;
    }

    public Rectangle() throws Exception {
        this(new Point(0,0), new Point(100,50));
    }

    public Point getLowerLeftCorner() {
        return lowerLeftCorner.clone();
    }

    public Point getTopRightCorner() {
        return topRightCorner.clone();
    }

    @Override
    public Rectangle clone() {
        try {
            return new Rectangle(this.lowerLeftCorner, this.topRightCorner);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean contains(Point point) {
        return  ((lowerLeftCorner.x <= point.x) && (point.x <= topRightCorner.x)) &&
                ((lowerLeftCorner.y <= point.y) && (point.y <= topRightCorner.y));
    }

    @Override
    public void move(int dx, int dy) {
        this.lowerLeftCorner.move(dx,dy);
        this.topRightCorner.move(dx,dy);
    }
}

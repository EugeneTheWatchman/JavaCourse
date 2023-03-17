package ru.croc.task4;

public class Rectangle extends Figure{
    Point lowerLeftCorner;
    Point topRightCorner;

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
}

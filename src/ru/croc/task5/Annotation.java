package ru.croc.task5;

class Annotation {
    private Figure selectedArea;
    private String text;
    public Annotation(Figure selectedArea, String text) throws Exception {
        this.setSelectedArea(selectedArea);
        this.text = text;
    }
    public Annotation(Figure selectedArea) throws Exception {
        this(selectedArea,"");
    }

    public void setSelectedArea(Figure selectedArea) throws Exception {
        if ( !(selectedArea instanceof Circle || selectedArea instanceof Rectangle) ) {
            throw new Exception("selectedArea must to be Circle or Rectangle");
            // If you are going to add a new Figure extend, loot at the override of the toString method too
        }
        this.selectedArea = selectedArea;
    }
    @Override
    public String toString() {
        if (selectedArea instanceof Circle) {
            Circle circle = (Circle) selectedArea;
            return String.format("C (%s, %s), %s: %s", circle.center.x, circle.center.y,
                    circle.radius, this.text);
        } else if (selectedArea instanceof Rectangle) {
            Rectangle rect = (Rectangle) selectedArea;
            return String.format("R (%s, %s), (%s, %s): %s", rect.lowerLeftCorner.x, rect.lowerLeftCorner.y,
                    rect.topRightCorner.x, rect.topRightCorner.y, this.text );
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Annotation annotation = new Annotation(new Rectangle(), "Прямоугольник");
        Annotation annotation2 = new Annotation(new Circle(), "Кружок");
        Annotation annotation1 = new Annotation(new Figure(),"Ошибка");
        System.out.println(annotation);
        System.out.println(annotation2);
        System.out.println(annotation1);
    }
}


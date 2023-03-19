package ru.croc.task5;

class Annotation implements Movable{
    private Figure selectedArea;
    private String label;

    public Annotation(Figure selectedArea, String label) throws Exception {
        this.setSelectedArea(selectedArea);
        this.label = label;
    }

    public Annotation(Figure selectedArea) throws Exception {
        this(selectedArea, "");
    }

    public void setSelectedArea(Figure selectedArea) throws Exception {
        if (!(selectedArea instanceof Circle || selectedArea instanceof Rectangle)) {
            throw new Exception("selectedArea must to be Circle or Rectangle");
            // If you are going to add a new Figure extend, loot at the override of the toString method too
        }
        this.selectedArea = selectedArea;
    }
    public Figure getSelectedArea() {
        return this.selectedArea.clone();
    }

    public String getLabel() {return this.label; }

    @Override
    public String toString() {
        if (selectedArea instanceof Circle) {
            Point center = ((Circle) selectedArea).getCenter();
            int radius = ((Circle) selectedArea).getRadius();
            return String.format("C (%s, %s), %s: %s", center.x, center.y,
                    radius, this.label);
        } else if (selectedArea instanceof Rectangle) {
            Point lowerLeftCorner = ((Rectangle) selectedArea).getLowerLeftCorner();
            Point topRightCorner = ((Rectangle) selectedArea).getTopRightCorner();
            return String.format("R (%s, %s), (%s, %s): %s", lowerLeftCorner.x, lowerLeftCorner.y,
                    topRightCorner.x, topRightCorner.y, this.label);
        }
        return null;
    }

    @Override
    public void move(int dx, int dy) {
        this.selectedArea.move(dx, dy);
    }
}


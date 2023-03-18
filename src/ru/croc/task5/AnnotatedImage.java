package ru.croc.task5;

class AnnotatedImage {
    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }
    public String getImagePath() {
        return this.imagePath;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint(int x, int y) {
        for (Annotation annotation : annotations) {
            if (annotation.getSelectedArea().contains(new Point(x,y))) {
                return annotation;
            }
        }
        return null;
    }
    public Annotation findByLabel(String label) {
        for (Annotation annotation: annotations) {
            if (annotation.getLabel().contains(label)) {
                return annotation;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Circle circle = new Circle(new Point(150,150),50);
        Annotation annotation1 = new Annotation(circle, "Кружочек");

        Rectangle rect = new Rectangle(new Point(0,0), new Point(100,200));
        Annotation annotation2 = new Annotation(rect, "Прямоугольник");

        Annotation[] annotations = new Annotation[] {annotation1,annotation2};
        AnnotatedImage annotatedImage = new AnnotatedImage(null, annotations);

        System.out.println(annotatedImage.findByLabel("Прямоуг"));  // найдет прямоугольник
        System.out.println(annotatedImage.findByPoint(150,150)); // найдет кружок
        annotatedImage.annotations[0].move(-50, -50); // убираем кружок на 100,100
        System.out.println(annotatedImage.findByPoint(150,150)); // ничего не найдет
    }
}



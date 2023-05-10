package ru.croc.task4;

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

    public static void main(String[] args) throws Exception {
        Circle circle = new Circle(new Point(150, 150), 50);
        Annotation annotation1 = new Annotation(circle, "Кружочек");

        Rectangle rect = new Rectangle(new Point(0, 0), new Point(100, 200));
        Annotation annotation2 = new Annotation(rect, "Прямоугольник");

        Annotation[] annotations = new Annotation[]{annotation1, annotation2};
        AnnotatedImage annotatedImage = new AnnotatedImage(null, annotations);

        for (Annotation annotation: annotatedImage.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
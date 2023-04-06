package ru.croc.task6;

public class IllegalPositionException extends RuntimeException{

    private String position;

    public IllegalPositionException(String text, String position) {
        super(String.format(text, position));
        this.position = position;
    }

    public IllegalPositionException(String position) {
        super("Неверная позиция:" + position);
        this.position = position;
    }

    public IllegalPositionException() {
        super();
    }
}

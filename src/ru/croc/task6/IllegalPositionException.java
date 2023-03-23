package ru.croc.task6;

public class IllegalPositionException extends RuntimeException{

    public IllegalPositionException(String text) {
        super(text);
    }

    public IllegalPositionException() {
        super();
    }
}

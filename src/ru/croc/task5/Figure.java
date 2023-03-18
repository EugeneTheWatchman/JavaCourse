package ru.croc.task5;


public abstract class Figure implements Movable{
    public abstract Figure clone();
    public abstract Boolean contains(Point point);
}

package ru.croc.task6;

public class IllegalMoveException extends Exception {
    private ChessPosition p1;
    private ChessPosition p2;

    public IllegalMoveException(ChessPosition p1, ChessPosition p2) {
        super("Ход запрещен: " + p1 + " -> " + p2);
        this.p1 = p1;
        this.p2 = p2;
    }

}

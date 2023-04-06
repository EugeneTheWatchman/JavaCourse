package ru.croc.task6;

public class Task6 {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Необходимо ввести 2 или больше аргументов,\nпредставляющих из себя наименование шахматных полей");
            return;
        }

        try {
            ChessPosition startPosition;
            ChessPosition endPosition;

            startPosition = ChessPosition.parse(args[0]);

            for (int i = 1; i < args.length; i++) {

                endPosition = ChessPosition.parse(args[i]);

                if (!Knight.isPossibleMove(startPosition, endPosition)) {
                    throw new IllegalMoveException(startPosition, endPosition);
                }

                startPosition = endPosition;
            }
        } catch (IllegalPositionException|IllegalMoveException e) {
            System.out.println(e);
            return;
        }

        System.out.println("OK");
    }
}

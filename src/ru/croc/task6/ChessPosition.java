package ru.croc.task6;

public class ChessPosition {

    public static final char[] LETTERS = new char[] {'a','b','c','d','e','f','g','h'};
    public static final char[] NUMBERS = new char[] {'1','2','3','4','5','6','7','8'};
    static final int MININDEX = 0;
    static final int MAXLETTERINDEX = LETTERS.length;
    static final int MAXNUMBERINDEX = NUMBERS.length; // допускаю не квадратную форму доски
    private int letterIndex;
    private int numberIndex;

    public ChessPosition(int letterIndex, int numberIndex) {
        setPositionByIndexes(letterIndex, numberIndex);
    }

    public static ChessPosition parse(String position) {
        if (position == null || position.length() != 2) {
            throw new IllegalPositionException("Позиция \"%s\" должна задаваться одной буквой и одной цифрой", position);
        }
        String lettersString = new String(ChessPosition.LETTERS);
        String numbersString = new String(ChessPosition.NUMBERS);

        int letterIndex = lettersString.indexOf(position.charAt(0));
        int numberIndex = numbersString.indexOf(position.charAt(1));

        if (letterIndex == -1) {
            throw new IllegalPositionException("Позиция \"%s\" должна начинаться с одной из букв: " + lettersString, position);
        } else if (numberIndex == -1) {
            throw new IllegalPositionException("Позиция \"%s\" должна заканчиваться одной из цифр: " + numbersString, position);
        }

        return new ChessPosition(letterIndex, numberIndex);
    }

    public void setPositionByIndexes(int letterIndex, int numberIndex) {
        if (letterIndex < MININDEX || letterIndex >= MAXLETTERINDEX) {
            throw new IllegalPositionException("Индекс буквы \"%s\" должен быть в пределах от " + MININDEX + " до " + (MAXLETTERINDEX-1), Integer.toString(letterIndex));
        } else if (numberIndex < MININDEX || numberIndex >= MAXNUMBERINDEX) {
            throw new IllegalPositionException("Индекс числа \"%s\"  должен быть в пределах от " + MININDEX + " до " + (MAXNUMBERINDEX-1), Integer.toString(numberIndex));
        }
        this.letterIndex = letterIndex;
        this.numberIndex = numberIndex;
    }

    public int[] getIndexesOfPosition() {
        return new int[] {letterIndex,numberIndex};
    }

    @Override
    public String toString() {
        return String.format("%s%s", ChessPosition.LETTERS[this.letterIndex], ChessPosition.NUMBERS[this.numberIndex]);
    }

    public static void main(String[] args) throws Exception {

        System.out.println(ChessPosition.parse(null));

        if (args.length < 2) {
            System.out.println("Необходимо ввести 2 или больше аргументов,\nпредставляющих из себя наименование шахматных полей");
            return;
        }

        ChessPosition startPosition;
        ChessPosition endPosition;

        try {
            startPosition = ChessPosition.parse(args[0]);
        } catch (IllegalPositionException e) {
            System.out.println(e);
            return;
        }

        for (int i = 1; i < args.length; i++) {

            try {
                endPosition = ChessPosition.parse(args[i]);
            } catch (IllegalPositionException e) {
                System.out.println(e.toString());
                return;
            }

            try {
                if (!Knight.isPossibleMove(startPosition, endPosition)) {
                    throw new IllegalMoveException(startPosition, endPosition);
                }
            } catch (IllegalMoveException e) {
                System.out.println(e);
                return;
            }


            startPosition = endPosition;
        }
        System.out.println("OK");
    }
}

package ru.croc.task6;

public class ChessPosition {

    private int letterIndex;
    private int numberIndex;

    public static final char[] letters = new char[] {'A','B','C','D','E','F','G','H'}; //new char[] {'a','b','c','d','e','f','g','h'};
    public static final char[] numbers = new char[] {'1','2','3','4','5','6','7','8'};

    public ChessPosition(int letterIndex, int numberIndex) {
        setPositionByIndexes(letterIndex, numberIndex);
    }

    public ChessPosition() {
        this(0, 0);
    }

    static ChessPosition parse(String position) {
        if (position.length() != 2) {
            throw new IllegalPositionException("Позиция должна задаваться одной буквой и одной цифрой");
        }
        //Pattern pattern = Pattern.compile("[a-h][1-8]");
        //Pattern pattern = Pattern.compile(String.format("[%s][%s]", new String(ChessPosition.letters), new String(ChessPosition.numbers)));
        /*Matcher matcher = pattern.matcher(position);
        if (!matcher.matches()) {
            throw new IllegalPositionException("Позиция должна задаваться буквой от a до h и цифрой от 1 до 8");
        }*/
        String lettersString = new String(ChessPosition.letters);
        String numbersString = new String(ChessPosition.numbers);

        int letterIndex = lettersString.indexOf(position.charAt(0));
        int numberIndex = numbersString.indexOf(position.charAt(1));

        if (letterIndex == -1) {
            throw new IllegalPositionException(String.format("Позиция должна начинаться с одной из букв: %s", lettersString));
        } else if (numberIndex == -1) {
            throw new IllegalPositionException(String.format("Позиция должна заканчиваться одной из цифр: %s", numbersString));
        }

        return new ChessPosition(letterIndex, numberIndex);
    }

    public void setPositionByIndexes(int letterIndex, int numberIndex) {
        if (letterIndex < 0 || letterIndex >= ChessPosition.letters.length) {
            throw new IllegalPositionException(String.format("Индекс буквы должен быть в пределах от %s до %s", 0, ChessPosition.letters.length-1));
        } else if (numberIndex < 0 || numberIndex >= ChessPosition.numbers.length) {
            throw new IllegalPositionException(String.format("Индекс числа должен быть в пределах от %s до %s", 0, ChessPosition.letters.length-1));
        }
        this.letterIndex = letterIndex;
        this.numberIndex = numberIndex;
    }

    public int[] getIndexesOfPosition() {
        return new int[] {letterIndex,numberIndex};
    }

    @Override
    public String toString() {
        return String.format("%s%s", ChessPosition.letters[this.letterIndex], ChessPosition.numbers[this.numberIndex]);
    }

    public static void main(String[] args) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                ChessPosition p1 = new ChessPosition(x,y);
                ChessPosition p2 = ChessPosition.parse("Dd");
                //System.out.print(p1 + "\t");
                System.out.print(((Horse.isPossibleMove(p1,p2)) ? p1 : 0) + "\t");
            }
            System.out.print("\n");
        }

    }
}

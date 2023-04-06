package ru.croc.task6;
import static java.lang.Math.abs;

public class Knight {

    public static boolean isPossibleMove(ChessPosition firstPosition, ChessPosition secondPosition) {

        int[] firstPositionIndexes = firstPosition.getIndexesOfPosition();
        int[] secondPositionIndexes = secondPosition.getIndexesOfPosition();

        int deltaX = abs(firstPositionIndexes[0] - secondPositionIndexes[0]);
        int deltaY = abs(firstPositionIndexes[1] - secondPositionIndexes[1]);

        return deltaX == 1 && deltaY == 2 ||
                deltaX == 2 && deltaY == 1;
    }
}

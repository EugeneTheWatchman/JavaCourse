package ru.croc.task6;
import static java.lang.Math.abs;

public class Horse {

    static boolean isPossibleMove(ChessPosition firstPosition, ChessPosition secondPosition) {
        int[] firstPositionIndexes = firstPosition.getIndexesOfPosition();
        int[] secondPositionIndexes = secondPosition.getIndexesOfPosition();
        return ((abs(firstPositionIndexes[0] - secondPositionIndexes[0]) == 1) && (abs(firstPositionIndexes[1] - secondPositionIndexes[1]) == 2)) ||
                (abs(firstPositionIndexes[0] - secondPositionIndexes[0]) == 2) && (abs(firstPositionIndexes[1] - secondPositionIndexes[1]) == 1);
    }
}

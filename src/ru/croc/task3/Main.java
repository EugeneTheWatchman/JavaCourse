package ru.croc.task3;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            return;
        }

        int[] vector = readVector(args);

        int[] minMaxIndexes = findMinMaxIndexes(vector);
        int minIndex = minMaxIndexes[0];
        int maxIndex = minMaxIndexes[1];

        vector = swapElements(vector, 0, minIndex);
        if (maxIndex == 0) {
            maxIndex = minIndex; // после первого swap'a максимальное число переехало по адресу minIndex
        }
        vector = swapElements(vector, vector.length - 1, maxIndex);

        printOutVector(vector);
    }

    public static int[] findMinMaxIndexes(int[] vector) {
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] < vector[minIndex]) {
                minIndex = i;
            }
            if (vector[i] > vector[maxIndex]) {
                maxIndex = i;
            }
        }
        return new int[] {minIndex, maxIndex};
    }

    public static void printOutVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + " ");
        }
    }

    public static int[] swapElements(int[] vector, int index1, int index2) {
        int[] vecClone = vector.clone();
        int temp = vecClone[index1];
        vecClone[index1] = vecClone[index2];
        vecClone[index2] = temp;
        return vecClone;
    }

    public static int[] readVector(String[] args) {
        int[] vector = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            vector[i] = Integer.parseInt(args[i]);
        }
        return vector;
    }
}
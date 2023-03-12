package ru.croc.task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            return;
        }

        int[] vector = readVector(args);

        int[] minMaxIndexes = findMinMaxIndexes(vector);
        int minIndex = minMaxIndexes[0];
        int maxIndex = minMaxIndexes[1];

        vector = swapIndexes(vector,0,minIndex);
        vector = swapIndexes(vector, vector.length-1 ,maxIndex);

        printOutVector(vector);
    }

    public static int[] findMinMaxIndexes(int[] vector) {
        int minIndex = 0, maxIndex = 0;
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
    public static int[] swapIndexes(int[] vector, int index1, int index2) {
        int[] vecClone = vector.clone(); // по идее тут это только мешает, но изменять объект передаваемый.... странно было бы
        int temp = vecClone[index1];
        vecClone[index1] = vecClone[index2];
        vecClone[index2] = temp;
        return vecClone;
    }

    public static int[] readVector(String[] args) {
        int[] vector = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            vector[i] =
                    new Scanner(args[i]+' ').nextInt();
        }
        return vector;
    }
}
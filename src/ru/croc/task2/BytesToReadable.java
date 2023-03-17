package ru.croc.task2;

import java.util.Scanner;

public class BytesToReadable {
    public static void main(String[] args) {
        if (args.length == 0) { return; }
        Scanner scanner = new Scanner(args[0]);
        double bytes = scanner.nextDouble();
        printBytes(bytes);
    }

    public static void printBytes(double bytes) {
        String[] units = {"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB", "RB", "QB"}; // больше приставок СИ не нашел
        int index = 0;
        while (bytes >= 1024 && index < units.length - 1) {
            bytes /= 1024;
            index++;
        }
        System.out.printf("%.1f %s", bytes, units[index]);
    }
}

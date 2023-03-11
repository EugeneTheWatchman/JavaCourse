package ru.croc.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) { return; }
        Scanner scanner = new Scanner(args[0]);
        long bytes = scanner.nextLong();
        printBytes(bytes);
    }
    public static void printBytes(long bytes) {
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        double size = bytes;
        int index = 0;
        while (size >= 1024 && index < units.length - 1) {
            size /= 1024;
            index++;
        }
        System.out.printf("%.1f %s", size, units[index]);
    }
}

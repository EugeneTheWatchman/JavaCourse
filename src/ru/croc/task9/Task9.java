package ru.croc.task9;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Task9 {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Первый аргумент должен указывать пуль файла");
            return;
        }

        String path = args[0];
        path = ".\\src\\ru\\croc\\task9";

        new LogFilesFinder(new String[] {path});//.getFilesInDirectory(".\\src\\ru\\croc\\task9",".MD");

    }


    public static String readFile(String fileName) {

        Charset charset = StandardCharsets.UTF_8;

        var builder = new StringBuilder();

        try (var reader = new FileReader(fileName, charset);
             var bufferedReader = new BufferedReader(reader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e);
        }

        return builder.toString();
    }

    public static BufferedReader getFileReader(String fileName) throws IOException {

        Charset charset = StandardCharsets.UTF_8;

        FileReader reader = new FileReader(fileName, charset);

        return new BufferedReader(reader);
    }

}

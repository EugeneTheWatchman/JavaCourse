package ru.croc.task8;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {

    public static void main(String[] args) {

        if (args.length == 0) {
            return;
        }

        String path = args[0];
        String someText = readFile(path);
        System.out.println(countWords(someText));
    }

    public static String readFile(String fileName) {

        Charset charset = StandardCharsets.UTF_8;

        var builder = new StringBuilder();

        try (var reader = new FileReader(fileName, charset)) {
            int data;

            while ((data = reader.read()) != -1) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e);
        }
        return builder.toString();
    }

    public static int countWords(String someText) {
        String simpleRegularExpression = "\\b[а-яА-Яa-zA-Z_0-9]+\\b"; // почему-то \w даёт другой результат, а еще, если поставить вместо "+" шаблон "{n,m}" то можно искать слова определенных длин, забавно
        Pattern pattern = Pattern.compile(simpleRegularExpression);

        int counter = 0;

        Matcher matcher = pattern.matcher(someText);
        while (matcher.find()) {
            counter++;
            /* for debug
            int startIndex = matcher.start();
            int endIndex = matcher.end();
            System.out.println(someText.substring(startIndex, endIndex));
            */
        }
        return counter;
    }
}

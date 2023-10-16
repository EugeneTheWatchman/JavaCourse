package ru.croc.task7;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    public static void main(String[] args) {
        String codeWithComents = readFile("input.java");
        String codeWithoutComenst = deleteComments(codeWithComents);
        writeFile("output.txt", codeWithoutComenst);
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
            System.out.println("Ошибка ввода");
        }
        return builder.toString();
    }

    public static void writeFile(String fileName, String dataToWrite) {
        try (var writer = new FileWriter(fileName);
             var bufferedWriter = new BufferedWriter(writer);) {

            bufferedWriter.append(dataToWrite);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Ошибка вывода");
        }
    }

    public static String deleteComments(String codeWithCommetns) {

        String result;

        String singleLineRegex = "//.*";
        result = codeWithCommetns.replaceAll(singleLineRegex, "");

        String multiLineRegex = "/\\*(.|\\s)*?\\*/";
        result = result.replaceAll(multiLineRegex, "");

        return result;
    }
}

package ru.utils.fileFinder;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            FileFinder fileFinder = new FileFinder(args[0],args[1]);
            System.out.println("Найдено файлов: " + fileFinder.getFiles().length);
        } catch (FileNotFoundException e) {
            System.out.printf("Путь \"%s\" не найден", args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введите два аргумента: \"путь к папке для поиска\" \"имя файла\"");
        }

    }
}

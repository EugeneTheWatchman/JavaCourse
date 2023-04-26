package ru.croc.finalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public final class LeartIt {

    //private static final DataBase;

    private static boolean isAdmin;
    // для доступа к CRUD операциям

    private static User logIn(String login) {
        // TODO
        // check DB
        // if login in DB, return some userData
        // if login not in DB, create new user and write it to DB
        return new User(true, 0);
    }

    public static void main(String[] args) {
        System.out.println("Введите логин:");
        Scanner scan = new Scanner(System.in);
        String login = scan.nextLine();

        User userData = logIn(login);
        isAdmin = userData.isAdministrator;
    }

    private Test readTestFromDB(long testID) {
        // в базе будут храниться XML файлы?
        // если нет, то читаем данные теста и создаём новый тест
        // если да, то используем InsertWordTest.importFromXML(), только там пока что путь до XML в аргументах
        String description = "Insert a right variant of verb \"be\"";
        String sentence = "My favorite politician and actor %s Donald Trump";
        String[] variantsToInsert = new String[] {"is", "are", "were", "was"};

        Test test = new InsertWordTest(description, sentence, variantsToInsert);
        test.ID = testID;

        return test;
    }

    // для других тестов переопределяются другие типы, т.к. заранее не знаю их структру
    private boolean runTest(InsertWordTest test) {
        System.out.println(test.description);
        System.out.println(test.sentence);
        System.out.println("Variants:");

        List<String> shuffledVariants = new ArrayList<>(List.of(test.variantsToInsert));
        Collections.shuffle(shuffledVariants);

        for (int i = 0; i < shuffledVariants.size(); i++) {
            System.out.println(i + 1 + ")" + shuffledVariants.get(i));

        }
        System.out.print("Enter a right variant number: ");
        Scanner scanner = new Scanner(System.in);
        int rightVariant = scanner.nextInt() - 1;

        while (rightVariant < 1 || rightVariant > test.variantsToInsert.length) {
            System.out.println("Нет такого варианта ответа");
            rightVariant = scanner.nextInt() - 1;
        }
        if (shuffledVariants.get(rightVariant).equals(test.variantsToInsert[0])) {
            System.out.println("Выбран правильный вариант ответа");
            return true;
        } else {
            System.out.println("Выбран неправильный вариант ответа");
            return false;
        }
    }

}

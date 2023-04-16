package ru.croc.task12;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

public class Task12 {

    // static fields for debug only
    static final String HASH = "40682260CC011947FC2D0B1A927138C5"; //Hasher.hashPassword("qqqqqqq");
    static final PassWordGenerator PASS_WORD_GENERATOR = new PassWordGenerator("qwertyuiopasdfghjklzxcvbnm", 7);
    static final boolean DEBUG = false;

    public static void main(String[] args) {
        if (DEBUG) {
            int maxNumberOfThreads = 30;
            Duration[] durations = new Duration[maxNumberOfThreads];
            for (int i = 1; i < maxNumberOfThreads; i++) {
                durations[i] = measureTime(i);
            }
            System.out.println(Arrays.toString(durations));
        } else {
            try {
                if (args.length < 2) {
                    throw new ArrayIndexOutOfBoundsException("Неправильные аргументы.\n" +
                            "Первый аргумент задает кол-во потоков\n" +
                            "Второй агрумент задает хэш искомого пароля");
                }
                int numOfThreads = Integer.parseInt(args[0]);
                String hash = args[1];
                var passGen = new PassWordGenerator("qwertyuiopasdfghjklzxcvbnm", 7);
                remindPassword(numOfThreads, hash, passGen);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public static Duration measureTime(int numberOfThreads) {

        LocalTime time = LocalTime.now();
        try {
            for (Thread thread : remindPassword(3, HASH, PASS_WORD_GENERATOR)) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        } finally {
            return Duration.between(time, LocalTime.now());
        }
    }

    public static Thread[] remindPassword(int numberOfThreads, String expectedHash, PassWordGenerator passWordGenerator) {

        long maxIndex = passWordGenerator.getNumberOfVariants() - 1;

        Thread[] threads = new Thread[numberOfThreads];

        long interval = maxIndex / numberOfThreads + 1;

        for (int i = 0; i < numberOfThreads; i++) {
            long min = interval * i;
            long max = Math.min(interval * (i + 1) - 1, maxIndex);

            threads[i] = new Thread(new HashVavidatorThred(min, max, expectedHash, passWordGenerator));
            threads[i].start();
        }
        return threads;
    }
}

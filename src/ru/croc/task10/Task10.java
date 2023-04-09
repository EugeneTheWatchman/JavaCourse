package ru.croc.task10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Task10 {
    public static final char SEPARATOR = ',';

    public static void main(String[] args) {

        // читаем файл и записываем время начала и конца звонка в соответствующие массивы
        ArrayList<Long>[] startEndTime = readFile();
        ArrayList<Long> callsStartTime = startEndTime[0];
        ArrayList<Long> callsEndTime = startEndTime[1];

        int countOfCallsNow = 0;
        int maxCountOfCalls = 0;
        long start;
        long end;
        while ((callsEndTime.size() + callsStartTime.size()) > 0) {

            if (callsStartTime.size() != 0) {
                start = callsStartTime.get(0);
            } else {
                start = Long.MAX_VALUE;
            }
            if (callsEndTime.size() != 0 ) {
                end = callsEndTime.get(0);
            } else {
                // если закончились окончания звонков, то и начала закончились, значит цикл не должен был пойти на итерацию
                break;
            }

            if (start <= end) {
                countOfCallsNow++; // разговор начался
                callsStartTime.remove(0); // убираем время начала разговора из возможных, т.к. мы его использовали уже
            } else {
                countOfCallsNow--; // разговор закончился
                callsEndTime.remove(0); // убираем время конца разговора из возможных, т.к. мы его использовали уже
            }

            if (maxCountOfCalls < countOfCallsNow) { // сравниваем максимальное кол-во звонков единовременно с текущим
                maxCountOfCalls = countOfCallsNow; // обновляем максимальное, при необходимости
            }
        }

        System.out.println("Максимальное время звонков одновременно: " + maxCountOfCalls);
    }

    public static ArrayList<Long>[] readFile() {
        // никаких проверок не осуществляю на правильность ввода, считаем что ввод всегда релевантный
        ArrayList<Long> callsStartTime = new ArrayList<>();
        ArrayList<Long> callsEndTime = new ArrayList<>();

        try (var reader = new FileReader("src/ru/croc/task10/input.txt")) {
            var bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int separatorIndex = line.indexOf(SEPARATOR);

                long startTime = Long.parseLong(line.substring(0,separatorIndex));
                long endTime = Long.parseLong(line.substring(separatorIndex+1));

                callsStartTime.add(startTime);
                callsEndTime.add(endTime);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода / вывода");
        }
        // обязательно сортируем
        Collections.sort(callsStartTime);
        Collections.sort(callsEndTime);

        return new ArrayList[] {callsStartTime, callsEndTime};
    }

}

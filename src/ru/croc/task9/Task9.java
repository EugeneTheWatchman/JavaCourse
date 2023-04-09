package ru.croc.task9;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class Task9 {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("В качестве аргумента командной строки укажите путь к директории, в которой находятся файлы логов");
            return;
        } // валидация входных данных
        String path = args[0];

        ArrayList<StringAndItsBufferedReaderStructure> linesAndReaders = new ArrayList<>(); // объявляем тут, чтобы было видно в блоке finally, где будем закрывать ридеры
        try (var writer = new FileWriter(path + "\\output.txt");
             var bufferedWriter = new BufferedWriter(writer)) { // нам понадобится 1 writer, инициализируем его

            LogFilesFinder logFilesFinder = new LogFilesFinder(path, new String[] {".log",".trace"}); // объект который при создании находит все файлы в директории и во всех вложенных директориях с заданным расширением

            linesAndReaders = getLinesAndReaders(logFilesFinder); // пробегаемся по всем найденным файлам и создаём список ридеров и соответсвующих им строк

            int indexWithEarliesLine = compareLinesByTimeAndGetIdOfTheEarliest(linesAndReaders); // вычисляем индекс наиболее ранней строки в логах
            while (indexWithEarliesLine >= 0) { // в случае не нахождения наиболее ранней строки, индекс будет равен -1
                var earliestLineAndItsReader = linesAndReaders.get(indexWithEarliesLine);

                bufferedWriter.append(earliestLineAndItsReader.line); // записываем в файл
                bufferedWriter.newLine();
                bufferedWriter.flush();

                earliestLineAndItsReader.line = earliestLineAndItsReader.reader.readLine(); // заместо записанной строки читаем следующую
                indexWithEarliesLine = compareLinesByTimeAndGetIdOfTheEarliest(linesAndReaders); // вычисляем индекс наиболее ранней строки в логах
            }

        } catch (IOException e) {
            System.out.println(e);
            return;
        } finally {
            for (var lineAndItsReader : linesAndReaders) {
                try {
                    lineAndItsReader.reader.close();
                } catch (IOException e) {
                    System.out.println(e);
                    return;
                }
            }
        }
    }

    public static ArrayList<StringAndItsBufferedReaderStructure> getLinesAndReaders(LogFilesFinder logFilesFinder) throws IOException {

        ArrayList<StringAndItsBufferedReaderStructure> linesAndReaders = new ArrayList<>();

        for (File file : logFilesFinder.getFiles()) { // пробегаемся по всем найденным файлам и создаём список ридеров и соответсвующих им строк
            BufferedReader reader = getFileReader(file);
            String line = reader.readLine();

            var lineAndItsReader = new StringAndItsBufferedReaderStructure(line, reader);
            linesAndReaders.add(lineAndItsReader);
        }

        return linesAndReaders;
    }

    public static BufferedReader getFileReader(File fileName) throws IOException {

        Charset charset = StandardCharsets.UTF_8;

        FileReader reader = new FileReader(fileName, charset);

        return new BufferedReader(reader);
    }

    /**
     * @return index of the first (by time) line, or -1
     */
    private static int compareLinesByTimeAndGetIdOfTheEarliest(ArrayList<StringAndItsBufferedReaderStructure> lines) {
        long earliestTime = Long.MAX_VALUE;
        int indexOfEarliestLine = -1;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).line;

            if (line == null) {
                continue;
            }
            long time = Long.parseLong(line.substring(0,line.indexOf(" "))); // c нулевого индекса до первого пробела
            if (time < earliestTime) {
                earliestTime = time;
                indexOfEarliestLine = i;
            }
        }

        return indexOfEarliestLine;
    }
}

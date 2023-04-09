package ru.croc.task9;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class Task9 {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("В качестве аргумента командной строки принимает путь к директории, в которой находятся файлы логов");
            return;
        }
        String path = args[0];

        LogFilesFinder logFilesFinder;
        try {
            logFilesFinder = new LogFilesFinder(path, new String[] {".log"});
        } catch (FileNotFoundException|RuntimeException e) {
            System.out.println(e);
            return;
        }

        ArrayList<StringAndItsBufferedReaderStructure> linesAndReaders = new ArrayList<>();
        try (var writer = new FileWriter("src\\ru\\croc\\task9\\logs\\output.txt");
             var bufferedWriter = new BufferedWriter(writer);) {


            for (File file : logFilesFinder.getFiles()) {
                BufferedReader reader = getFileReader(file);
                String line = reader.readLine();

                var lineAndItsReader = new StringAndItsBufferedReaderStructure(line, reader);
                linesAndReaders.add(lineAndItsReader);
            }

            int indexWithEarliesLine = compareLinesByTimeAndGetIdOfTheEarliest(linesAndReaders);
            while (indexWithEarliesLine >= 0) {
                var earliestLineAndItsReader = linesAndReaders.get(indexWithEarliesLine);
                bufferedWriter.append(earliestLineAndItsReader.line);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                earliestLineAndItsReader.line = earliestLineAndItsReader.reader.readLine();
                indexWithEarliesLine = compareLinesByTimeAndGetIdOfTheEarliest(linesAndReaders);
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

    public static BufferedReader getFileReader(File fileName) throws IOException {

        Charset charset = StandardCharsets.UTF_8;

        FileReader reader = new FileReader(fileName, charset);

        return new BufferedReader(reader);
    }

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

package ru.croc.task9;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

public class LogFilesFinder {

    public LogFilesFinder(String[] pathsToDirectoriesWithLogs, String[] logFilesExtensions) {


        /**
         * @param   logFilesExtensions
         *          String array with filename extensions with dots
         *
         */
    }

    public LogFilesFinder(String[] pathsToDirectoriesWithLogs) {
        this(pathsToDirectoriesWithLogs, new String[] {"log","trace"});
    }

    public File[] getSubdirectoriesInDirectory (String pathToDirectory) {

    }

    public File[] getFilesInDirectory(String pathToDirectory, String logFilesExtension) throws FileNotFoundException{
        File file = new File(pathToDirectory);
        if(!file.exists()) {
            throw new FileNotFoundException(pathToDirectory + " папка не существует");
        }

        return file.listFiles(new FileNameExtentionFilter(logFilesExtension));
    }
}

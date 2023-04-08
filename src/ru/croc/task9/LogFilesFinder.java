package ru.croc.task9;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class LogFilesFinder {
    private File[] files;

    public LogFilesFinder(String[] pathsToDirectoriesWithLogs, String[] logFilesExtensions) throws FileNotFoundException {
        /**
         * @param   logFilesExtensions
         *          String array with filename extensions with dots
         *
         */
        ArrayList<File> files = new ArrayList<File>();

        for (String path : pathsToDirectoriesWithLogs) {
            for (String acceptableExtension : logFilesExtensions) {
                files.addAll(List.of(getFilesInDirectoryAndSubdirectiories(path, acceptableExtension)));
            }
        }

        this.files = files.toArray(new File[0]);
    }

    public LogFilesFinder(String[] pathsToDirectoriesWithLogs) throws FileNotFoundException {
        this(pathsToDirectoriesWithLogs, new String[] {"log","trace"});
    }

    public File[] getFiles() {
        return files;
    }

    public File[] getSubdirectoriesInDirectory (String pathToDirectory) throws FileNotFoundException {
        File file = new File(pathToDirectory);
        if(!file.exists()) {
            throw new FileNotFoundException(pathToDirectory + " папка не существует");
        }

        return file.listFiles(new SubDirectoriesFilter());
    }

    public File[] getFilesInDirectory(String pathToDirectory, String logFilesExtension) throws FileNotFoundException{
        File file = new File(pathToDirectory);
        if(!file.exists()) {
            throw new FileNotFoundException(pathToDirectory + " папка не существует");
        }

        return file.listFiles(new FileNameExtentionFilter(logFilesExtension));
    }

    public File[] getFilesInDirectoryAndSubdirectiories(String pathToDirectory, String logFilesExtension) throws FileNotFoundException{
        ArrayList<File> files = new ArrayList<File>();

        File[] subDirectories = getSubdirectoriesInDirectory(pathToDirectory);
        File[] filesInCurrentDirectory = getFilesInDirectory(pathToDirectory, logFilesExtension);

        files.addAll(List.of(filesInCurrentDirectory));

        for (File subDir : subDirectories) {
            filesInCurrentDirectory = getFilesInDirectoryAndSubdirectiories(subDir.getAbsolutePath(), logFilesExtension);
            files.addAll(List.of(filesInCurrentDirectory));
        }
        return files.toArray(new File[0]);
    }
}

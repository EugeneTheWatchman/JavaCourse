package ru.croc.task9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LogFilesFinder {
    private final File[] files;
    private final String pathsToDirectoryWithLogs;

    private final String[] logFilesExtensions;

    /**
     *
     * @param pathsToDirectoryWithLogs String, not null
     * @param logFilesExtensions String array with filename extensions with dots, not null
     * @throws FileNotFoundException if directory not found
     */
    public LogFilesFinder(String pathsToDirectoryWithLogs, String[] logFilesExtensions) throws FileNotFoundException {

        this.pathsToDirectoryWithLogs = pathsToDirectoryWithLogs;
        this.logFilesExtensions = logFilesExtensions;

        ArrayList<File> files = new ArrayList<File>();

        for (String acceptableExtension : logFilesExtensions) {
            files.addAll(List.of(getFilesInDirectoryAndSubdirectiories(pathsToDirectoryWithLogs, acceptableExtension)));
        }

        this.files = files.toArray(new File[0]);
    }

    /**
     * Search for files with extensions {".log",".trace"}
     *
     * @param pathsToDirectoryWithLogs String, not null
     * @throws FileNotFoundException if directory not found
     */
    public LogFilesFinder(String pathsToDirectoryWithLogs) throws FileNotFoundException {
        this(pathsToDirectoryWithLogs, new String[] {".log",".trace"});
    }

    public File[] getFiles() {
        return files;
    }

    public String[] getFilesExtensions() {
        return logFilesExtensions;
    }

    public String getPathsToDirectory() {
        return pathsToDirectoryWithLogs;
    }

    /**
     * @param pathToDirectory String, not null
     * @return Array of subdirs
     * @throws FileNotFoundException if directory not found
     */
    public static File[] getSubdirectoriesInDirectory (String pathToDirectory) throws FileNotFoundException {
        File file = new File(pathToDirectory);
        if(!file.exists()) {
            throw new FileNotFoundException(pathToDirectory + " папка не существует");
        }

        return file.listFiles(new SubDirectoriesFilter());
    }

    /**
     *
     * @param pathToDirectory String, not null
     * @param logFilesExtension String, not null
     * @return Array of files in current dir with current extension
     * @throws FileNotFoundException if directory not found
     */
    public static File[] getFilesInDirectory(String pathToDirectory, String logFilesExtension) throws FileNotFoundException{
        File file = new File(pathToDirectory);
        if(!file.exists()) {
            throw new FileNotFoundException(pathToDirectory + " папка не существует");
        }

        return file.listFiles(new FileNameExtentionFilter(logFilesExtension));
    }

    /**
     *
     * @param pathToDirectory String, not null
     * @param logFilesExtension String, not null
     * @return Array of files in current dir and subdirs with current extension
     * @throws FileNotFoundException if directory not found
     */
    public static File[] getFilesInDirectoryAndSubdirectiories(String pathToDirectory, String logFilesExtension) throws FileNotFoundException{
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

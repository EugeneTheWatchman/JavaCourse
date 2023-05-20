package ru.utils.fileFinder;

import ru.croc.task9.SubDirectoriesFilter;
import ru.utils.fileFinder.FileNameFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private final File[] files;
    private final String pathsToSearchDirectory;

    private final String fileName;

    /**
     *
     * @param pathsToDirectory String, not null
     * @param fileName String filename to search, not null
     * @throws FileNotFoundException if directory not found
     */
    public FileFinder(String pathsToDirectory, String fileName) throws FileNotFoundException {

        this.pathsToSearchDirectory = pathsToDirectory;
        this.fileName = fileName;

        ArrayList<File> files = new ArrayList<File>();

        files.addAll(List.of(getFilesInDirectoryAndSubdirectiories(pathsToDirectory, fileName)));


        this.files = files.toArray(new File[0]);
    }

    public File[] getFiles() {
        return files;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPathsToSearchDirectory() {
        return pathsToSearchDirectory;
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
     * @param fileName String, not null
     * @return Array of files in current dir with current extension
     * @throws FileNotFoundException if directory not found
     */
    public static File[] getFilesInDirectory(String pathToDirectory, String fileName) throws FileNotFoundException{
        File file = new File(pathToDirectory);
        if(!file.exists()) {
            throw new FileNotFoundException(pathToDirectory + " папка не существует");
        }

        return file.listFiles(new FileNameFilter(fileName));
    }

    /**
     * @param pathToDirectory String, not null
     * @param fileName String, not null
     * @return Array of files in current dir and subdirs with current extension
     * @throws FileNotFoundException if directory not found
     */
    public static File[] getFilesInDirectoryAndSubdirectiories(String pathToDirectory, String fileName) throws FileNotFoundException{
        ArrayList<File> files = new ArrayList<File>();

        File[] subDirectories = getSubdirectoriesInDirectory(pathToDirectory);
        File[] filesInCurrentDirectory = getFilesInDirectory(pathToDirectory, fileName);

        if (filesInCurrentDirectory != null) {

            for (int i = 0; i < filesInCurrentDirectory.length; i++) {
                File file = filesInCurrentDirectory[i];
                System.out.println(file.getAbsolutePath());
            }

            files.addAll(List.of(filesInCurrentDirectory));
        }

        if (subDirectories != null) {
            for (File subDir : subDirectories) {
                filesInCurrentDirectory = getFilesInDirectoryAndSubdirectiories(subDir.getAbsolutePath(), fileName);
                files.addAll(List.of(filesInCurrentDirectory));
            }
        }

        return files.toArray(new File[0]);
    }
}

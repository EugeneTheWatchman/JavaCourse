package ru.utils.fileFinder;

import java.io.File;
import java.io.FilenameFilter;

public class FileNameFilter implements FilenameFilter {

    private final String fileName;

    public FileNameFilter(String fileName) {
        this.fileName = fileName.toLowerCase();
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().contains(this.fileName);
    }
}

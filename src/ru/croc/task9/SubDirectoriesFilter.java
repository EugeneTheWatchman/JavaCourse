package ru.croc.task9;

import java.io.File;
import java.io.FilenameFilter;

public class SubDirectoriesFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return new File(dir,name).isDirectory();
    }
}

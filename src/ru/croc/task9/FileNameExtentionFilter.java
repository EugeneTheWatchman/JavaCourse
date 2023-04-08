package ru.croc.task9;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

public class FileNameExtentionFilter implements FilenameFilter {

    private final String extention;

    public FileNameExtentionFilter(String extention) {
        this.extention = extention.toLowerCase();
    }

    @Override
    public boolean accept(File dir, String name) {
        return new File(dir,name).isFile() && name.toLowerCase().endsWith(this.extention);
    }
}

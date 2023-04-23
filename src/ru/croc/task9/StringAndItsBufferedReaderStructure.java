package ru.croc.task9;

import java.io.BufferedReader;

public class StringAndItsBufferedReaderStructure {
    public String line;
    public final BufferedReader reader;

    public StringAndItsBufferedReaderStructure(String line, BufferedReader bufferedReader) {
        this.line = line;
        this.reader = bufferedReader;
    }

}
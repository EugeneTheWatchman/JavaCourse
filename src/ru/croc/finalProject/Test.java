package ru.croc.finalProject;

public class Test {

    public long ID;

    public static Test importFromXML(String pathToFile) {
        return new Test();
    }

    public boolean exportToJSON(String pathToFile) {
        return true; // true if export success
    }
}

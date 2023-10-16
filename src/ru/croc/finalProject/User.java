package ru.croc.finalProject;

import java.util.ArrayList;

public class User {

    public boolean isAdministrator;
    public long ID;
    // TODO
    // data about passed tests

    public ArrayList<Long> passedTestIDs = new ArrayList<>();
    public ArrayList<Long> failedTestIDs = new ArrayList<>();

    public User(boolean isAdmin, long ID) {
        isAdministrator = isAdmin;
        this.ID = ID;
    }

    public void addTest(long testID, boolean isPassed) {
        //isPassed ? passedTestIDs.add(testID) : failedTestIDs.add(testID); почему-то так не даёт записать
        if (isPassed) {
            passedTestIDs.add(testID);
        } else {
            failedTestIDs.add(testID);
        }
    }


}

package ru.croc.task13;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Task13 {
    public static void main(String[] args) {
        CommentsFilter task = new CommentsFilter();
        List<String> strings = new ArrayList<>(List.of("I love potAto chips", "I lovE Donald Trump", "I don't love anybody"));

        System.out.println("До фильтрации: "+ strings);
        task.filterComments(strings , Set.of("trump"));
        System.out.println("После фильтрации: "+ strings);
    }
}

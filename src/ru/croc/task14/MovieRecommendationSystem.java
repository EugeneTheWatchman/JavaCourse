package ru.croc.task14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieRecommendationSystem {

    private static final String MOVIES_FILE = "src\\ru\\croc\\task14\\movies.txt";
    private static final String HISTORY_FILE = "src\\ru\\croc\\task14\\history.txt";

    public static void main(String[] args) {
        Map<Integer, String> movies = loadMovies();
        List<User> users = loadUsersHistory();

        System.out.println("Enter your movie history (comma-separated list of movie IDs):");
        String input = new Scanner(System.in).nextLine();
        String[] inputIds = input.split(",");

        User mainUser = new User();
        for (String id : inputIds) {
            mainUser.addFilm(Integer.parseInt(id));
        }

        Set<Integer> uniqueFilmsForRecomendations = new HashSet<>();
        for (User user : users) {
            if (user.isHalfFilmsWatched(mainUser.getFilms())) {
                uniqueFilmsForRecomendations.addAll(user.getFilms());
            }
        }
        uniqueFilmsForRecomendations.removeAll(mainUser.getFilms());

        Integer recomendMovie = -1;
        int maxViews = 0;
        for (Integer movie : uniqueFilmsForRecomendations) {
            int views = 0;
            for (User user : users) {
                views += user.countTimesFilmWatched(movie);
            }
            if (views >= maxViews) {
                maxViews = views;
                recomendMovie = movie;
            }
        }

        System.out.println(movies.get(recomendMovie));
    }

    private static Map<Integer, String> loadMovies() {
        Map<Integer, String> movies = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                movies.put(id, title);
            }
        } catch (IOException e) {
            System.err.println("Error loading movies: " + e.getMessage());
        }
        return movies;
    }

    private static List<User> loadUsersHistory() {
        List<User> users = new ArrayList<>();

        List<Integer> movieIds = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                movieIds.clear();
                for (String id : parts) {
                    movieIds.add(Integer.parseInt(id));
                }

                User user = new User(movieIds);
                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error loading history: " + e.getMessage());
        }
        return users;
    }
}
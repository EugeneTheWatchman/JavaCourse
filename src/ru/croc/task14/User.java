package ru.croc.task14;

import java.util.*;

public class User {
    private Collection<Integer> films;

    public User() {
        films = new ArrayList<>();
    }

    public User(Collection<Integer> films) {
        this.films = new ArrayList<>(films) {
        };
    }

    public void addFilm(Integer movieId) {
        films.add(movieId);
    }

    public Collection<Integer> getFilms() {
        return new ArrayList<>(films);
    }

    // хотел сделать чтобы метод принимал юзера, но уловие "общих вкусов" не симметрично и поэтому если бы этот метод вызвать не от того юзера, то можно легко запутаться
    public boolean isHalfFilmsWatched(Collection<Integer> moviesIds) {
        if (moviesIds == null || moviesIds.size() == 0) {
            return false;
        }

        Set<Integer> films = new HashSet<>(moviesIds);

        int count = 0;
        for (Integer film : films) {
            if (films.contains(film)) {
                count++;
            }
        }

        return count * 2 >= films.size();
    }

    public int countTimesFilmWatched(Integer movieId) {
        int count = 0;
        for (Integer film : films) {
            if (film.equals(movieId)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        User user = new User(List.of(2,1,3));
        boolean b = user.isHalfFilmsWatched(List.of(1,10,10));
        System.out.println(b);
    }
}

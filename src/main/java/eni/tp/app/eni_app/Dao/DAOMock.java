package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DAOMock implements IDAOMovie {
    List<Movie> movies = Arrays.asList(
            new Movie(1L, "once upon a time in the west", 1968, 166, "Un mystérieux inconnu possédant un harmonica s'associe à un desperado notoire pour protéger une jolie veuve d'un assassin sans scrupule travaillant pour le chemin de fer."
           ,"/once.jpg" ),
            new Movie(2L, "The Godfather", 1972, 175, " Le patriarche vieillissant d'une dynastie de la mafia New Yorkaise passe le flambeau de son empire clandestin à son fils réticent."
            ,"/godfather.jpg"));
    @Override
    public List<Movie> selectMovie() {

        return movies;
    }

    @Override
    public Movie selectMovieById(long id) {
        Movie movieToFound = movies.stream().filter(movie -> movie.id == id).findFirst().orElse(null);

        return movieToFound;
    }
}


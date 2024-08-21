package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DAOMock implements IDAOMovie {

    @Override
    public List<Movie> selectMovie() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(1L,"once upon a time in the west",1968,166,
                "Un mystérieux inconnu possédant un harmonica s'associe à un desperado notoire pour protéger une jolie veuve d'un assassin sans scrupule travaillant pour le chemin de fer."
        ));
        movies.add(new Movie(2L,"The Godfather",1972,175,
               " Le patriarche vieillissant d'une dynastie de la mafia New Yorkaise passe le flambeau de son empire clandestin à son fils réticent."
        ));
        return movies;
    }
}

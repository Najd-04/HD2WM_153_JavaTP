package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Movie;

import java.util.List;

public interface IDAOMovie {
    List<Movie> selectMovie();
    Movie selectMovieById( long id);

    void saveMovie(Movie movie);
}

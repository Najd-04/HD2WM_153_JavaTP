package eni.tp.app.eni_app.Bll;

import eni.tp.app.eni_app.Bo.Movie;
import eni.tp.app.eni_app.Dao.IDAOMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ArticleManager {
        @Autowired
         IDAOMovie daoMovie;

        public  List<Movie> getMovies() {


            List<Movie> movies = daoMovie.selectMovie();

            return movies;
        }
    public  Movie getById(long id) {

        Movie movie = daoMovie.selectMovieById(id);

        return movie;

    }
    public void saveMovie(Movie movie) {
        daoMovie.saveMovie(movie);
    }
}

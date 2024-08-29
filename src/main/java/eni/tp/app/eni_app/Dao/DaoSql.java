package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile("mysql")
@Component
public class DaoSql implements IDAOMovie {
    @Autowired
    JdbcTemplate jdbcTemplate;
    static final RowMapper<Movie> MOVIE_ROW_MAPPER = new RowMapper<Movie>() {

        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            movie.id = rs.getLong("id");
            movie.title = rs.getString("title");
            movie.note = rs.getInt("note");
            movie.year = rs.getInt("year");
            movie.duration = rs.getInt("duration");
            movie.synopsis = rs.getString("synopsis");
            movie.url = rs.getString("url");

            return movie;
        }
    };

    @Override
    public List<Movie> selectMovie() {
        return jdbcTemplate.query("SELECT* FROM movie", MOVIE_ROW_MAPPER);

    }

    @Override
    public Movie selectMovieById(long id) {
        List<Movie> movies = jdbcTemplate.query("SELECT* FROM movie WHERE id = ?", MOVIE_ROW_MAPPER, id);
        if (movies.size() == 0) {
            return null;
        }
        return movies.get(0);
    }
    @Override
    public void saveMovie(Movie movie) {
        if ( movie.getId() != null && selectMovieById(movie.getId()) != null) {
            jdbcTemplate.update("UPDATE movie SET title = ?, year = ?, duration = ?, synopsis = ? WHERE id =?",
                    movie.title, movie.year, movie.duration, movie.synopsis,movie.id);
            return;
        }
        jdbcTemplate.update("INSERT INTO movie(id,title,note,year,duration,synopsis,url) VALUES (?,?,?,?,?,?,?)", movie.id, movie.title,movie.note,movie.year,movie.duration,movie.synopsis,movie.url);
    }
}


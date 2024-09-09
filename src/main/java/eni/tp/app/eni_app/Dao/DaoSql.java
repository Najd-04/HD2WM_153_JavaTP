package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Movie;
import eni.tp.app.eni_app.Bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile("mysql")
@Component
public class DaoSql implements IDAOMovie {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        String sql = "INSERT INTO movie (id,title,note,year,duration,synopsis,url,id_genre) VALUES (:idMovie,:titleMovie,:noteMovie," +
                ":yearMovie, :durationMovie, :synopsisMovie, :photoMovie, :idgenre)";

//On renseigne les paramètres attendus dans la requête
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idMovie", movie.getId());
        mapSqlParameterSource.addValue("titleMovie", movie.getTitle());
        mapSqlParameterSource.addValue("noteMovie", movie.getNote());
        mapSqlParameterSource.addValue("yearMovie", movie.getYear());
        mapSqlParameterSource.addValue("durationMovie", movie.getDuration());
        mapSqlParameterSource.addValue("synopsisMovie", movie.getSynopsis());
        mapSqlParameterSource.addValue("photoMovie", movie.getUrl());
        mapSqlParameterSource.addValue("idgenre",movie.getGenre().getId()) ;
        //Insérer en base un aliment
        namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
//        jdbcTemplate.update("INSERT INTO movie(id,title,note,year,duration,synopsis,url) VALUES (?,?,?,?,?,?,?)", movie.id, movie.title,movie.note,movie.year,movie.duration,movie.synopsis,movie.url);
        Long lastMovieId = namedParameterJdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", new MapSqlParameterSource(), Long.class);

        String sqlInsertActeurs = "INSERT INTO ACTEURS (id_film, id_participant) VALUES (:idFilm, :idParticipant)";

// Parcourir la liste des participants et insérer chaque relation film-participant
        if (movie.getParticipants() != null && !movie.getParticipants().isEmpty()) {
            for (Participant participant : movie.getParticipants()) {
                MapSqlParameterSource mapSqlParameterSourceActeurs = new MapSqlParameterSource();
                mapSqlParameterSourceActeurs.addValue("idFilm", lastMovieId);
                mapSqlParameterSourceActeurs.addValue("idParticipant", participant.getId());

                // Exécution de la requête pour insérer chaque relation film-participant
                namedParameterJdbcTemplate.update(sqlInsertActeurs, mapSqlParameterSourceActeurs);
            }
        }

    }
}
//// Exécuter la requête pour insérer le film
//namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
//
//// 2. Récupérer l'id du film récemment inséré (si auto-incrémenté par la base de données)
//Long childrenMovieId = namedParameterJdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", new MapSqlParameterSource(), Long.class);
//
//// 3. Insérer la liste des participants (acteurs) dans la table acteurs
//String sqlActeur = "INSERT INTO acteurs (id_film, id_participant) VALUES (:idMovie, :idParticipant)";
//for (Participant participant : movie.getParticipants()) {
//MapSqlParameterSource mapParticipantSource = new MapSqlParameterSource();
//    mapParticipantSource.addValue("idMovie",childrenMovieId);
//    mapParticipantSource.addValue("idParticipant", participant.getId()); // Utiliser l'ID du participant
//
//        namedParameterJdbcTemplate.update(sqlActeur, mapParticipantSource);

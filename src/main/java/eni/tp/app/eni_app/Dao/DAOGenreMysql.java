package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOGenreMysql implements IDAOGenre{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Genre> findAll() {

        String sql = "select id,titre from genre";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Genre>(Genre.class));
    }

    @Override
    public Genre findById(int id) {
        String sql = "select id,titre from genre where id= :idGenre";

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("idGenre", id);

        return namedParameterJdbcTemplate.queryForObject(sql, map, new BeanPropertyRowMapper<>(Genre.class));
    }
}




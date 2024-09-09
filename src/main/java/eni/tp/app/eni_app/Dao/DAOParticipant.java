package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public class DAOParticipant implements IDAOParticipant{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Participant> findAll() {

        String sql = "select id,nom,prenom from participant";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Participant>(Participant.class));
    }

    @Override
    public Participant findById(Long id) {
        String sql = "select id,nom,prenom from participant where id= :idParticipant";

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("idParticipant", id);

        return namedParameterJdbcTemplate.queryForObject(sql, map, new BeanPropertyRowMapper<>(Participant.class));
    }
}

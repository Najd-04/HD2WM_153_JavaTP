package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.User;
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
public class DAOMySqlUser implements IDAOAuth {
    @Autowired
    JdbcTemplate jdbcTemplate;
    static final RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.id = rs.getInt("id");
            user.nom = rs.getString("nom");
            user.prenom = rs.getString("prenom");
            user.email = rs.getString("email");
            user.password = rs.getString("password");
            user.admin = rs.getBoolean("admin");


            return user;
        }
    };

    @Override
    public User login(int id, String nom, String prenom, String email, String password, boolean admin) {
        return null;
    }

    @Override
    public List<User> selectMovie() {
        return List.of();
    }

    @Override
    public List<User> selectUser() {
        return jdbcTemplate.query("SELECT* FROM user", USER_ROW_MAPPER);

    }

    @Override
    public void saveMovie(User user) {

    }

    @Override
    public User selectUserByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT* FROM user WHERE email = ?",USER_ROW_MAPPER, email);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public void saveUser(User user) {
        if ( user.getEmail() != null && selectUserByEmail(user.getEmail()) != null) {
            jdbcTemplate.update("UPDATE user SET nom = ?, prenom = ?, email = ?, password = ? WHERE id =?",
                    user.nom, user.prenom, user.email, user.password,user.id);
            return;
        }
        jdbcTemplate.update("INSERT INTO user(id,nom,prenom,email,password,admin) VALUES (?,?,?,?,?,?)", user.id, user.nom,user.prenom,user.email,user.password,user.admin);
    }
}

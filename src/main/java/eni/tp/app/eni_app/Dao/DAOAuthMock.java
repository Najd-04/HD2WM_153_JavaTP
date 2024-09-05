package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Movie;
import eni.tp.app.eni_app.Bo.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Profile("mock")
@Component
public class DAOAuthMock implements IDAOAuth {
List<User> users = Arrays.asList(

        new User(),
        new User()
);
    public User login(String email, String password) {
        User userToFound = users.stream().filter(user ->
                user.email.equals(email) && user.password.equals(password)).findFirst().orElse(null);
        return userToFound;
    }

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
        return List.of();
    }

    @Override
    public void saveMovie(User user) {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public User selectUserByEmail(String email) {
        return null;
    }
}

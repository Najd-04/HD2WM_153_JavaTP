package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.User;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IDAOAuth {


  public User login(int id, String nom, String prenom, String email, String password, boolean admin);

  List<User> selectMovie();

  List<User> selectUser();

  void saveMovie(User user);

  void saveUser(User user);
   User selectUserByEmail(String email) ;

  User login(String email, String password);
}

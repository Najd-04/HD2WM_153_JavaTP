package eni.tp.app.eni_app.Bll;

import eni.tp.app.eni_app.Bo.User;
import eni.tp.app.eni_app.Dao.IDAOAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutManager {
    @Autowired
    IDAOAuth daoAuth;
public User selectUserByEmail(String email) {

return daoAuth.selectUserByEmail(email);


}

    public EniManagerResponse<User> authenticate(String email, String password) {
//        on va essayer de trouver le user qui l'email et le password envoyés
        User userToFound = daoAuth.login(email, password);

//        si couple email/password incorrect erreur code 756
        if (userToFound == null) {
            return EniManagerResponse.performResponse("756", "Couple email/password incorrect !!", null);

        }
//      sinon code 200

        return EniManagerResponse.performResponse("202", "Vous êtes connecté(e) avec succés", userToFound);
    }
}

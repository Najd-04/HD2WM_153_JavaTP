package eni.tp.app.eni_app.ihm;

import eni.tp.app.eni_app.Bll.AutManager;
import eni.tp.app.eni_app.Bll.EniManagerResponse;
import eni.tp.app.eni_app.Bo.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;

@SessionAttributes({"loggedUser","sessionUser"})
@Controller
public class FormController {
    @Autowired
    AutManager autManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;  // JdbcTemplate pour exécuter des requêtes SQL

    @GetMapping("login")
    public String showLogin(Model model, RedirectAttributes redirectAttributes) {
        User loggedUser = (User) model.getAttribute("loggedUser");
        if (loggedUser != null) {
            EniIhmHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_ERROR, "Erreur vous êtes deja connecté(e)");

            return "redirect:/acceuil";
        }
        User user = new User();
        model.addAttribute("user", user);
        return "v1/login";
    }

    @PostMapping("login")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        // 1 :: controle surface
        //erreur : si controle de surface
        //todo :retourner  la page avec les erreurs de validation le format
        if (bindingResult.hasErrors()) {
            return "v1/login";
        }


        //2 : co,trole metier le manager
//        erreur code 756 retourner la page avec l'erreur metier'
        EniManagerResponse<User> response = autManager.authenticate(user.email, user.password);
        if (response.code.equals("756")) {
            return "v1/login";
        }
        //3 : connecter l'user en session
        //mettre l'user dans la session

        model.addAttribute("loggedUser", response.data);
//        EniIhmHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCCESS, "Vous êtes bien connecté(e) avec succés");
        EniIhmHelpers.sendSuccesslashMessage(redirectAttributes, "Vous êtes bien connecté(e) avec succés");


        return "redirect:/list";
    }

    @GetMapping("logout")
    public String logout(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        // nettoyer la session (se déconnecter)
        sessionStatus.setComplete();
        EniIhmHelpers.sendSuccesslashMessage(redirectAttributes, "Vous êtes déconnecté(e) ");

        // rediriger à la page d'accueil
        return "redirect:/acceuil";

    }
    @GetMapping("miseEnSessionUser")
    public String miseEnSessionUser(Principal principal,Model model) {

        User sessionUser = autManager.selectUserByEmail(principal.getName());
        model.addAttribute("sessionUser", sessionUser);
return "/acceuil";
    }
}

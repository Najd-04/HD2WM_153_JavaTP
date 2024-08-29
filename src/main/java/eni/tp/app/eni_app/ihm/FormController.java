package eni.tp.app.eni_app.ihm;

import eni.tp.app.eni_app.Bo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({"loggedUser"})
@Controller
public class FormController {
    @GetMapping("login")
    public String showLogin(Model model,RedirectAttributes redirectAttributes) {
        User loggedUser = (User) model.getAttribute("loggedUser");
        if (loggedUser != null) {
            EniIhmHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_ERROR,"Erreur vous êtes deja connecté(e)");

            return "redirect:/acceuil";
        }
        User user = new User();
        model.addAttribute("user", user);
        return "v1/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("loggedUser", user);
//        EniIhmHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCCESS, "Vous êtes bien connecté(e) avec succés");
        EniIhmHelpers.sendSuccesslashMessage(redirectAttributes, "Vous êtes bien connecté(e) avec succés");


        return "redirect:/list";
    }

    @GetMapping("logout")
    public String logout(SessionStatus sessionStatus,RedirectAttributes redirectAttributes) {
        // nettoyer la session (se déconnecter)
        sessionStatus.setComplete();
        EniIhmHelpers.sendSuccesslashMessage(redirectAttributes, "Vous êtes déconnecté(e) ");

        // rediriger à la page d'accueil
        return "redirect:/acceuil";
    }


}

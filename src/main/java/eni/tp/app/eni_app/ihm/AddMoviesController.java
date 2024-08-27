package eni.tp.app.eni_app.ihm;

import eni.tp.app.eni_app.Bo.Movie;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AddMoviesController {
    @GetMapping("create-movie")
    public String showformulaire(Model model) {

        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "formulaire-film";
    }

    @PostMapping("create-movie")
    public String postFormulaire(@Valid @ModelAttribute("movie") Movie movie,BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            System.out.println("Erreur de controle");


            return "formulaire-film";
        }
        model.addAttribute("createMovie", movie);

        EniIhmHelpers.sendSuccesslashMessage(redirectAttributes, "Le film est enregistrer avec succés");
        return "redirect:/list";
    }
}

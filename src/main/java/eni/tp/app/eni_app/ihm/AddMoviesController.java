package eni.tp.app.eni_app.ihm;

import eni.tp.app.eni_app.Bll.ArticleManager;
import eni.tp.app.eni_app.Bo.Movie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AddMoviesController {
    @Autowired
   ArticleManager articleManager;

    public AddMoviesController(ArticleManager articleManager) {
        this.articleManager = articleManager;
    }

    @GetMapping({"create-movie/{id}","create-movie"})
    public String showformulaire(@PathVariable(required = false) Long id, Model model) {

        Movie movie = new Movie();
//        afficher un film existant dans le formulaire
        if(id != null) {
            movie = articleManager.getById(id);
        }
        model.addAttribute("movie", movie);
        return "formulaire-film";
    }

    @PostMapping("create-movie")
    public String postFormulaire(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            System.out.println("Erreur de controle");


            return "formulaire-film";
        }
        model.addAttribute("createMovie", movie);


        EniIhmHelpers.sendSuccesslashMessage(redirectAttributes, "Le film est enregistrer avec succés");
        articleManager.saveMovie(movie);
        return "redirect:/list";
    }
}

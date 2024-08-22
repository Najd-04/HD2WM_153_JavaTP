package eni.tp.app.eni_app;

import eni.tp.app.eni_app.Bll.ArticleManager;
import eni.tp.app.eni_app.Bo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    ArticleManager articleManager;

    @GetMapping("acceuil")
    public String showMovies(Model model) {
        model.addAttribute("movies", articleManager.getMovies());
        return "acceuil";
    }

    @GetMapping("details/{id}")
    public String showAliment(@PathVariable("id") long id, Model model) {

        Movie movie = articleManager.getById(id);

        if (movie == null) {
            return "movie-not-found";
        }


        model.addAttribute("movie", movie);

        return "details-films";
    }

    @GetMapping("list")
    public String showlist(Model model) {
        List<Integer> maxStars = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("maxStars", maxStars);
        model.addAttribute("movies", articleManager.getMovies());
        return "list-films";
    }


}
package eni.tp.app.eni_app;

import eni.tp.app.eni_app.Bll.ArticleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
@Autowired
ArticleManager articleManager;
    @GetMapping("acceuil")
    public String showMovies() {


        return "acceuil";
    }

    @GetMapping("details")
    public String showdetails(Model model) {

        model.addAttribute("movies", articleManager.getMovies());

        return "details-films";
    }

    @GetMapping("list-films")
    public String showlist() {

        return "list-films";
    }
}


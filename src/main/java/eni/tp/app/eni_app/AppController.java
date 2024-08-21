package eni.tp.app.eni_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("login-acceuil")
    public String showacceuil() {

        return "acceuil";
    }
    @GetMapping("details")
    public String showdetails() {

        return "details-films";
    }
    @GetMapping("list-films")
    public String showlist() {

        return "list-films";
    }
}


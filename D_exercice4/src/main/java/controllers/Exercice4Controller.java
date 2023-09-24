package controllers;

import dtos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import services.Compteur;
import services.Facade;

import java.util.List;

@Controller
@SessionAttributes({"courant", "username", "mood", "compteur"})
@RequestMapping("/")
public class Exercice4Controller {

    @Autowired
    private Facade facade;

    @Autowired
    private Compteur compteur;

    @RequestMapping("")
    public String toLogin(Model model) {
        model.addAttribute(new User());
        return("login");
    }

    // on passe un objet user en paramètre : mapping automatique des champs du formulaire
    // on joue aussi avec les messages d'erreurs (BindingResult) ...
    @RequestMapping("login")
    public String checkLP(User user, Model model){
        if (facade.checkLP(user.getLogin(),user.getPassword())) {
            model.addAttribute("courant",user.getLogin());
            model.addAttribute("username",user.getLogin());
            model.addAttribute("moods", facade.getMoods());
            compteur.incrementingCpt();
            model.addAttribute("compteur", compteur.getCpt());
            return "welcome";
        } else {
            model.addAttribute("error","Les informations saisies ne correspondent pas à un utilisateur connu.");
            return "login";
        }
    }

    @RequestMapping("simplecheck")
    public String simpleCheck(@SessionAttribute String courant,Model model){
        System.out.println(courant);
        model.addAttribute("username",courant);
        compteur.incrementingCpt();
        model.addAttribute("compteur", compteur.getCpt());
        return "welcome";
    }

    @RequestMapping("logout")
    public String logout(SessionStatus status, Model model) {
        status.setComplete();
        model.addAttribute("courant",null);
        model.addAttribute(new User());
        return "login";
    }

    @RequestMapping("setMood")
    public String setMood(String mood, Model model) {
        model.addAttribute("mood", mood);
        return "welcome";
    }

    @ModelAttribute("moods")
    public List<String> populateMoods() {
        return facade.getMoods();
    }
}

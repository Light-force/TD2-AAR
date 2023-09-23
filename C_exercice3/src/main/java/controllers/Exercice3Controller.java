package controllers;

import dtos.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import services.Facade;

import java.util.List;

@Controller
@SessionAttributes({"courant", "username", "mood"})
@RequestMapping("/")
public class Exercice3Controller {
    private Facade facade = Facade.getInstance();

    @RequestMapping("")
    public String toLogin() {
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
            return "mood";
        } else {
            model.addAttribute("error","Les informations saisies ne correspondent pas à un utilisateur connu.");
            return "login";
        }
    }


    @RequestMapping("simplecheck")
    public String simpleCheck(@SessionAttribute String courant,Model model){
        System.out.println(courant);
        model.addAttribute("username",courant);
        return "welcome";
    }

    @RequestMapping("logout")
    public String logout(SessionStatus status) {
        status.setComplete();
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

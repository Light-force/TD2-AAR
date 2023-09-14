package controllers;

import dtos.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import services.Facade;

@Controller
@SessionAttributes({"courant", "userMood"})
@RequestMapping("/")
public class Exercice1Controller {
    private Facade facade=Facade.getInstance();

    @RequestMapping("")
    public String toLogin() {
        return("login");
    }

    // on passe un objet user en paramètre : mapping automatique des champs du formulaire
    // on joue aussi avec les messages d'erreurs (BindingResult) ...
    @RequestMapping("login")
    public String checkLP(User user, Model model){
        if (facade.checkLP(user.getLogin(),user.getPassword())) {
            model.addAttribute("courant", user.getLogin());
            model.addAttribute("username", user.getLogin());
            return "redirect:/mood";
        } else {
            model.addAttribute("error", "Les informations saisies ne correspondent pas à un utilisateur connu.");
            return "login";
        }
    }

    @RequestMapping("simplecheck")
    public String simpleCheck(@SessionAttribute String courant,@SessionAttribute String userMood, Model model){
        System.out.println(courant);
        model.addAttribute("username", courant);
        model.addAttribute("userMood", userMood);
        return "welcome";
    }

    @RequestMapping("logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "login";
    }

    @RequestMapping("mood")
    public String getMood(Model model) {
        model.addAttribute("moods", facade.getMoods());
        return "mood";
    }

    @RequestMapping(value = "setMood")
    public String setMood(String userMood, Model model) {
        model.addAttribute("userMood", userMood);
        return "redirect:/simplecheck";
    }
}

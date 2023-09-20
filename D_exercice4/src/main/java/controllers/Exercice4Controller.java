package controllers;

import dtos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import services.Compteur;
import services.Facade;

import java.util.List;

@Controller
@SessionAttributes({"courant", "mood", "compteur"})
@RequestMapping("/")
public class Exercice4Controller {

    @Autowired
    private Facade facade;

    @Autowired
    private Compteur compteur;

    @RequestMapping("")
    public String toLogin() {
        compteur.countInstances();
        return("login");
    }

    // on passe un objet user en paramètre : mapping automatique des champs du formulaire
    // on joue aussi avec les messages d'erreurs (BindingResult) ...
    @RequestMapping("login")
    public String checkLP(User user, Model model){
        compteur.countInstances();
        model.addAttribute("compteur", compteur.getNbInstances());
        if (facade.checkLP(user.getLogin(), user.getPassword())) {
            // on place courant dans le modèle, mais il s'agit d'un attribut de session, il se retrouve ainsi conservé en session
            model.addAttribute("courant", user.getLogin());
            model.addAttribute("username", user.getLogin());
            model.addAttribute("moods", facade.getMoods());
            return "welcome";
        } else {
            // on ajoute un simple message d'erreur au modèle...
            model.addAttribute("error","Les informations saisies ne correspondent pas à un utilisateur connu.");
            return "login";
        }
    }

    @RequestMapping("simplecheck")
    public String simpleCheck(@SessionAttribute String courant, Model model){
        compteur.countInstances();
        System.out.println(courant);
        model.addAttribute("username", courant);
        model.addAttribute("compteur", compteur.getNbInstances());
        return "welcome";
    }

    @RequestMapping("logout")
    public String logout(SessionStatus status) {
        compteur.countInstances();
        status.setComplete();
        return "login";
    }

    @RequestMapping("setMood")
    public String setMood(String mood, Model model) {
        compteur.countInstances();
        model.addAttribute("compteur", compteur.getNbInstances());
        model.addAttribute("mood", mood);
        return "welcome";
    }

    @ModelAttribute("moods")
    public List<String> populateMoods() {
        compteur.countInstances();
        return facade.getMoods();
    }
}

package services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Facade {

    private Map<String,String> users;

    private List<String> moods = Arrays.asList("Heureux", "Triste", "Excité", "Fatigué", "En colère");

    private int cpt;

    @PostConstruct
    public void initCpt() {
        this.cpt = 0;
    }

    private Facade(){
        users=new HashMap<>();
        users.put("alice","alice");
        users.put("bob","bob");
    }

    public boolean checkLP(String login,String password) {
        String pwd=users.get(login);
        return ((pwd!=null) && (pwd.equals(password)));
   }

    public List<String> getMoods() {
        return moods;
    }

    public int getCpt() {
        return cpt;
    }

    public void incrementingCpt() {
        ++this.cpt;
    }
}

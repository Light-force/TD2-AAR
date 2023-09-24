package services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Compteur {
    private int cpt;

    @PostConstruct
    public void initCpt() {
        this.cpt = 0;
    }

    public int getCpt() {
        return cpt;
    }

    public void incrementingCpt() {
        ++this.cpt;
    }
}

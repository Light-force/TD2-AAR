package services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Compteur {
    private int nbInstances;

    @PostConstruct
    public void resetNbInstances() {
        nbInstances = 0;
    }

    public void countInstances() {
        ++nbInstances;
    }

    public int getNbInstances() {
        return nbInstances;
    }
}

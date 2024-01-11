package org.mql.java.models;

public class Cours {
	
    private String nom;
    private int heures;

    public Cours(String nom, int heures) {
        this.nom = nom;
        this.heures = heures;
    }

    public String getNom() {
        return nom;
    }

    public int getHeures() {
        return heures;
    }
}

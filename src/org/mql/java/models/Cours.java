package org.mql.java.models;

import org.mql.java.annotation.isObject;

public class Cours {
	
    private String nom;
    private int heures;
    @isObject(type = "dependency")
    private Etudiant etudiant ;

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
    public void afficher() {
       etudiant = new Etudiant();
       System.out.println(etudiant.getId() + "---> " + etudiant.getCNE() + " :" +  etudiant.getFilier());
    }
    
}

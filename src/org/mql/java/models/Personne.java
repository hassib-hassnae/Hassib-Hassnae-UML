package org.mql.java.models;

import java.time.LocalDate;

public class Personne implements Data{
	private String nom;
	private String prenom;
	private int yearBirth;
	
	public Personne() {
		// TODO Auto-generated constructor stub
	}
	

	public Personne(String nom , String prenom , int yearBirth) {
		this.yearBirth = yearBirth;
		this.nom = nom;
		this.prenom = prenom;
	}


	@Override
	public int getData(int yearBirth) {
		return LocalDate.now().getYear() - yearBirth;
	}

}

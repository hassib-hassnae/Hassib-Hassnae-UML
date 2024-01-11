package org.mql.java.models;

import java.util.ArrayList;
import java.util.List;

import org.mql.java.annotation.isMqlist;
import org.mql.java.annotation.isObject;

@isMqlist
public class Etudiant extends Personne{

	
	private int id;
	private String CNE;
	private String filier;
	@isObject(type = "Aggregation")
	private Adresse adresse; //Agrégation
	@isObject(type = "Composition")
	 private List<Cours> cours; // Composition:La durée de vie des cours est liée à la durée de vie de l'étudiant
	 
	 public Etudiant() {
		
	}


	public Etudiant(String nom, String prenom, int age,int id, String cNE, String filier, Adresse adresse) {
		super(nom, prenom, age);
		this.id = id;
		CNE = cNE;
		this.filier = filier;
		this.adresse = adresse;
        this.cours = new ArrayList<>();

		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCNE() {
		return CNE;
	}


	public void setCNE(String cNE) {
		CNE = cNE;
	}


	public String getFilier() {
		return filier;
	}


	public void setFilier(String filier) {
		this.filier = filier;
	}
	private void test() {
		
	}
	
	

 }

package com.GestionAbsence.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Classe {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)  
	private Long id;
	@NotEmpty
	@Size(min=1, max=20)
	private String label;
	@NotEmpty
	@Size(min=3, max=20)
	private String nom_comp;
	
	
	@ManyToMany    
	private List <Matiere> matieres = new ArrayList<>() ; 
	
	@OneToMany (mappedBy = "classe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <Etudiant> etudiants =new ArrayList<>();
	
	@OneToMany (mappedBy = "classe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <Etudiant_Matiere_Classe> etudiant_matiere_classe;

	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classe(String label, String nom_comp, List<Matiere> matieres) {
		super();
		this.label = label;
		this.nom_comp = nom_comp;
		this.matieres = matieres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getNom_comp() {
		return nom_comp;
	}

	public void setNom_comp(String nom_comp) {
		this.nom_comp = nom_comp;
	}

	public List<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiere> matieres) {
		this.matieres = matieres;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public List<Etudiant_Matiere_Classe> getEtudiant_matiere_classe() {
		return etudiant_matiere_classe;
	}

	public void setEtudiant_matiere_classe(List<Etudiant_Matiere_Classe> etudiant_matiere_classe) {
		this.etudiant_matiere_classe = etudiant_matiere_classe;
	}





	
	
	
	
	
	
	
	

}

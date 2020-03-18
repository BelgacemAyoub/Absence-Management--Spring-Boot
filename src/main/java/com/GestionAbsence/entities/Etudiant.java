package com.GestionAbsence.entities;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity

public class Etudiant {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO) 
	private Long matricule;
	@NotEmpty
	@Size(min=3, max=20)
	private String nom;
	@NotEmpty
	@Size(min=3, max=20)
	private String prenom;
	@NotEmpty
	@Email
	private String email;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate date_naiss;
	
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Classe classe ;   
	
	@OneToMany (mappedBy = "etudiant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <Etudiant_Matiere_Classe> etudiant_matiere_classe;
	
	
	
	public Etudiant() {

	}

	public Etudiant(String nom, String prenom, String email, LocalDate date_naiss, Classe classe) {
		super();
	
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date_naiss = date_naiss;
		this.classe = classe;
	}
	

	public Long getMatricule() {
		return matricule;
	}

	public void setMatricule(Long matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate_naiss() {
		return date_naiss;
	}

	public void setDate_naiss(LocalDate date_naiss) {
		this.date_naiss = date_naiss;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public List<Etudiant_Matiere_Classe> getEtudiant_matiere_classe() {
		return etudiant_matiere_classe;
	}

	public void setEtudiant_matiere_classe(List<Etudiant_Matiere_Classe> etudiant_matiere_classe) {
		this.etudiant_matiere_classe = etudiant_matiere_classe;
	}

	@Override
	public String toString() {
		return "Etudiant [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", date_naiss=" + date_naiss + ", classe=" + classe.getId()+ "]";
	}


	
	
	
	
	

	
	



	
	
	
}


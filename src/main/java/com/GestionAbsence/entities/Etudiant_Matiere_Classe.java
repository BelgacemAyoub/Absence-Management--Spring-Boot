package com.GestionAbsence.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;



@Entity(name = "emc")

public class Etudiant_Matiere_Classe {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	@Min(value = 1)
	@Max(value = 2)
	private int nbre_seance;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate date_seance ;
	
	
    @ManyToOne
	private Etudiant etudiant;
    
    @ManyToOne
    private Classe classe;
    
    @ManyToOne
	private Matiere matiere;
    

	public Etudiant_Matiere_Classe() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Etudiant_Matiere_Classe(@NotEmpty int nbre_seance, LocalDate date_seance, Etudiant etudiant, Classe classe,
			Matiere matiere) {
		super();
		this.nbre_seance = nbre_seance;
		this.date_seance = date_seance;
		this.etudiant = etudiant;
		this.classe = classe;
		this.matiere = matiere;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getNbre_seance() {
		return nbre_seance;
	}


	public void setNbre_seance(int nbre_seance) {
		this.nbre_seance = nbre_seance;
	}


	public LocalDate getDate_seance() {
		return date_seance;
	}


	public void setDate_seance(LocalDate date_seance) {
		this.date_seance = date_seance;
	}


	public Etudiant getEtudiant() {
		return etudiant;
	}


	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}


	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}


	public Matiere getMatiere() {
		return matiere;
	}


	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}





	

}

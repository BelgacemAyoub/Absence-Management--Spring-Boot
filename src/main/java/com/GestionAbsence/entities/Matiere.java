package com.GestionAbsence.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity

public class Matiere{
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Long id;
	@Size(min=2,max=20)
	private String label;
	@Min(value = 1)
	@Max(value = 60)
	private Long Nbr_h;
	@Min(value = 1)
	@Max(value = 30)
	private Long Seuil_abs;
	
	@ManyToMany (mappedBy = "matieres", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  
	private List <Classe> classes;
	
	@OneToMany (mappedBy = "matiere", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <Etudiant_Matiere_Classe> etudiant_matiere_classe;

	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matiere(String label, Long nbr_h, Long seuil_abs) {
		super();
		this.label = label;
		Nbr_h = nbr_h;
		Seuil_abs = seuil_abs;
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

	public Long getNbr_h() {
		return Nbr_h;
	}

	public void setNbr_h(Long nbr_h) {
		Nbr_h = nbr_h;
	}

	public Long getSeuil_abs() {
		return Seuil_abs;
	}

	public void setSeuil_abs(Long seuil_abs) {
		Seuil_abs = seuil_abs;
	}

	public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}

	public List<Etudiant_Matiere_Classe> getEtudiant_matiere_classe() {
		return etudiant_matiere_classe;
	}

	public void setEtudiant_matiere_classe(List<Etudiant_Matiere_Classe> etudiant_matiere_classe) {
		this.etudiant_matiere_classe = etudiant_matiere_classe;
	}




	
	
	
	
	
	

	
	
	


	

}

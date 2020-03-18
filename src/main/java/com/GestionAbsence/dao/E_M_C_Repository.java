package com.GestionAbsence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.GestionAbsence.entities.Etudiant;
import com.GestionAbsence.entities.Etudiant_Matiere_Classe;


public interface E_M_C_Repository extends JpaRepository<Etudiant_Matiere_Classe, Long> {
	
	
	
	@Query ("select sum(e.nbre_seance * 1.5) from emc e where etudiant.matricule =:x and matiere.id =:y")
	public Float calculerAbsenceParMatiere (@Param ("x") Long matricule, @Param ("y") Long id);
	
	@Query ("select sum(e.nbre_seance * 1.5) from emc e where matiere.id =:x")
	public Float CalculerAbsenceGroupeParMatiere (@Param ("x") Long id);
	
	@Query("select distinct etudiant from emc" )
	public List<Etudiant> tousLesEtudiantAbsent();
	






}

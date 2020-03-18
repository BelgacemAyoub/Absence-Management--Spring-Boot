package com.GestionAbsence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.GestionAbsence.entities.Etudiant;



public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

	
	
	//Etudiant findByMatricule(Long matricule);

	void deleteByMatricule (Long matricule);
	
	@Query ("select e from Etudiant e where e.nom like :x")    			   
	public Page<Etudiant> chercher (@Param("x") String mc, Pageable pageable);  // la valeur de x c'est la valeur de paramétre mc
	
	@Query ("select e from Etudiant e where classe.id = :y")
	public List<Etudiant> findByClasse(@Param("y") Long id);
	
	@Query ("SELECT e FROM Etudiant e WHERE classe.id= :x")
	public ArrayList<Etudiant> chercherParClasse (@Param("x") Long id);
	
	
	
	
	
	
	

	
	
	
	
	

	
	
	
	
	
	
	
 
	
	
	

	
	






	

	


	

	
	
}

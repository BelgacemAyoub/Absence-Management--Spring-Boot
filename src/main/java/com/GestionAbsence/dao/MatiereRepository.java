package com.GestionAbsence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.GestionAbsence.entities.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

	@Query ("select m from Matiere m where m.label like :x")    			   
	Page<Matiere> chercher(@Param("x") String mc, Pageable pageable);

}

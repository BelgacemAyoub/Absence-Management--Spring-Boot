package com.GestionAbsence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.GestionAbsence.entities.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
	
	@Query ("select c from Classe c where c.label like :o")    			   
	public Page<Classe> chercher (@Param("o") String mc, Pageable pageable);
	
	// void deleteById (Long id);
    



}

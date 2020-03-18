package com.GestionAbsence.service;

public interface AbsenceService {
	
	public Float absenceEtudiantParMatiere (Long matricule, Long id);
	public Float absenceParMatiere (Long id);
	public void tousLesAbsence ();




}

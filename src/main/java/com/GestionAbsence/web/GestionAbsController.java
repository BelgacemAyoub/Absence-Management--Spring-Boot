package com.GestionAbsence.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.GestionAbsence.dao.ClasseRepository;
import com.GestionAbsence.dao.E_M_C_Repository;
import com.GestionAbsence.dao.EtudiantRepository;
import com.GestionAbsence.dao.MatiereRepository;
import com.GestionAbsence.entities.Classe;
import com.GestionAbsence.entities.Etudiant;
import com.GestionAbsence.entities.Etudiant_Matiere_Classe;
import com.GestionAbsence.entities.Matiere;
import com.GestionAbsence.service.AbsenceService;

@Transactional
@Controller
public class GestionAbsController {
	


	
	@Autowired
	ClasseRepository classeRepository;
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	E_M_C_Repository e_m_c_Repository;
	
	@Autowired
	MatiereRepository matiereRepository;
	
	
	@Autowired 
	AbsenceService absenceService;
	 
	
	Etudiant_Matiere_Classe emc=new Etudiant_Matiere_Classe();


	
	  @GetMapping ( value = "/absence") 
	  public String index (Model model, Classe classe) {   
		  model.addAttribute("classes", classeRepository.findAll());
		  

		  return "AbsenceManagement";	
	  }
	  
		@GetMapping("/AfficherEtudiants")
		public String test2(Model model,Classe classe) {

			List<Etudiant>listetud=new ArrayList<>();
			listetud= etudiantRepository.chercherParClasse(classe.getId());
			model.addAttribute("etudiants",listetud );

			return "EtudiantManagement";	
		}
	  
	  
	/*
	 * @GetMapping( value = "/AfficherEtudiants") public String afficher (Model
	 * model,Classe classe) {
	 * 
	 * System.out.println("*******"+classe.getId()); model.addAttribute("etudiants",
	 * etudiantRepository.findByClasse(classe.getId())); return
	 * "EtudiantManagement"; }
	 */
		
			
	  
	  @GetMapping (value = "/FormAbsences")
	  public String formEtudiant (Model model, Etudiant_Matiere_Classe emc, Long matricule, Long id) {
		  
		  
		  Optional<Classe> classe = classeRepository.findById(id);
		//Etudiant e = etudiantRepository.findByMatricule(matricule);
		  Etudiant e = etudiantRepository.getOne(matricule);
		  
		  
		  model.addAttribute("classe", classe.get());
		  model.addAttribute("etudiant", e);
		  
		  List <Matiere> matiere = classe.get().getMatieres();
		  model.addAttribute("matieres", matiere );	  
		  model.addAttribute("emc", emc);

		  return "FormAbsence";
	  	  
	  }

	  
	  @PostMapping (value = "/SaveAbsence")
	  public String SaveAbs (Model model, @Valid Etudiant_Matiere_Classe emc, Long matricule, Long id, BindingResult bindingResult) {
		
		  if (bindingResult.hasErrors()) 
			  
			  return "FormAbsence";
			  
			  e_m_c_Repository.save(emc);
		  
			  //float x= absenceEtudiantParMatiere(emc.getEtudiant().getMatricule(), emc.getMatiere().getId()) ;
			  return "redirect:/AfficherEtudiants?classe="+emc.getClasse().getId();	

		
		  
		  }
	  
	  
	  @GetMapping ("/nbrAbsEtudiantParMatiere")
	  public String nbrAbsEtudiantParMatiere (Model model, Long id, Long matricule) {
		  
		  Optional<Classe> classe = classeRepository.findById(id);
		//Etudiant etudiant = etudiantRepository.findByMatricule(matricule);
		  List<Matiere> matieres = classe.get().getMatieres();
		  
		  model.addAttribute("matricule", matricule);
		  model.addAttribute("id", id);
		  model.addAttribute("matieres", matieres);
          //model.addAttribute("matricule", matricule);
          
          return "AbsEtudiantParMatiere";
          
		  
	  }
	  
	  @PostMapping ("/nbrAbsEtudiantParMatiere") 
	  public String nbrAbsEtudiantParMat (Model model,  @RequestParam Long matricule,  @RequestParam Long id,  @RequestParam Long matiere) {
		  
		  System.out.println("****** matiere : "+ matiere);
		  String eliminatoire="";
		  Float nbr_heure = 0F;
		  Float nbr_heure_groupe= 0F;
		  
		  nbr_heure =  absenceService.absenceEtudiantParMatiere(matricule, matiere);
		  System.out.println("****** matricule : "+ matricule);
		  System.out.println("****** abs : "+ nbr_heure);
		  nbr_heure_groupe= absenceService.absenceParMatiere(matiere);
		  System.out.println("****** nbr_heure_groupe : "+ nbr_heure_groupe);
		  if(nbr_heure_groupe == null) {
			  nbr_heure_groupe=0F;
		   }
		   
		   if(nbr_heure == null) {
			   nbr_heure=0F;
		   }
		   
		  Optional<Matiere> m= matiereRepository.findById(matiere);
		  if((float)m.get().getSeuil_abs() <= nbr_heure) {
			  eliminatoire="Eliminé pour cette matiére";
		  }else {
			  eliminatoire="Il n'a pas dépassé la seille de cette matiére";  
		  }
		  model.addAttribute("nbr_heure",nbr_heure);
		  model.addAttribute("eliminatoire", eliminatoire);
		  model.addAttribute("nbr_heure_groupe", nbr_heure_groupe);
		
		  return "EliminationEtudiantParMatiere";
		  
	  }
	  
	  
	  
	  @Scheduled(fixedRate = 604800000) 
	  public void mailingAbsence() {
		  absenceService.tousLesAbsence(); 
		  }
	  
}
	  
	  
	 
	  
	  
	  
		  

		
	  
		  
		  
	  


	  
	  
	  

	  
	  
	  

	  
	  
	  
	  
	  



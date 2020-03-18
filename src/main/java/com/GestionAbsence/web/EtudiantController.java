package com.GestionAbsence.web;


/* on n'a pas besoin de déploier DispatcherServlet dans un projet 
 * spring boot parce que déja déploier par défaut par contre il faut
 * déclarer dispatcherServlet dans un projet web dynamique dans le 
 * fichier web.xml.  
 */


import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.GestionAbsence.dao.ClasseRepository;
import com.GestionAbsence.dao.EtudiantRepository;
import com.GestionAbsence.entities.Classe;
import com.GestionAbsence.entities.Etudiant;

/**
 * @author ayoub
 *
 */
@Transactional
@Controller
public class EtudiantController {
	
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private ClasseRepository classeRepository;
	
	
	
	
	
	  @RequestMapping ( value = "/index", method = RequestMethod.GET) 
	  public String index (Model model,
	      @RequestParam(name = "page", defaultValue = "0") int p,
	      @RequestParam(name = "size", defaultValue = "8") int s,
	      @RequestParam(name = "motCle", defaultValue = "") String mc) {     		// on va lire le paramétre motCle et on va l'affecte à mc 
		  
		  Pageable pageable = PageRequest.of(p, s);
		  Page<Etudiant>pageEtudiants = etudiantRepository.chercher("%"+mc+"%", pageable);
		  model.addAttribute("listEtudiants", pageEtudiants.getContent());
		  int[] pages = new int[pageEtudiants.getTotalPages()]; 
		  model.addAttribute("pages", pages);
		  model.addAttribute("size", s);             
		  model.addAttribute("pageCourante", p);  							
		  model.addAttribute("motCle", mc); 	
		  return "Etudiant";	
	  }
	      

	  
	  @RequestMapping (value = "/FormEtudiant", method = RequestMethod.GET)
	  public String formEtudiant (Model model, Etudiant etudiant) {
		  model.addAttribute("etudiant", new Etudiant());

		  Collection<Classe> classes = classeRepository.findAll();
		  model.addAttribute("listClasses",classes);
		  return "FormEtudiant";
	  	  }
	  
	  
	  
	  
	  @RequestMapping (value = "/SaveEtudiant", method = RequestMethod.POST)
	  public String save (@Valid Etudiant etudiant, BindingResult bindingResult) {
		  
		  if (bindingResult.hasErrors())
			  return "FormEtudiant";
		  System.out.println("*******************"+etudiant.getDate_naiss());
		  etudiantRepository.save(etudiant);

		  //model.addAttribute("matri", etudiant.getMatricule());

		 // System.out.println(etudiant.toString());
		  return "ConfirmationEtudiant";
	  	  }
	  
	  @RequestMapping (value = "/UpdateEtudiant", method = RequestMethod.POST)
	  public String update (@Valid Etudiant etudiant, BindingResult bindingResult) {
		  
		  if (bindingResult.hasErrors())
			  return "EditEtudiant";
		  etudiantRepository.save(etudiant);

		  return "ConfirmationEtudiant";
	  	  }
	   
	  
	  
	  @RequestMapping (value = "/delete", method = RequestMethod.GET)
	  public String delete (Long matricule) {
		  etudiantRepository.deleteByMatricule(matricule);
		  return "redirect:/index";

	      }
	  
	  @RequestMapping (value = "/EditEtudiant", method = RequestMethod.GET)
	  public String EditEtudiant (Model model, Long matricule) {
		  Etudiant e = etudiantRepository.getOne(matricule);
		  model.addAttribute("etudiant", e);
		  Collection<Classe> classes = classeRepository.findAll();
		  model.addAttribute("listClasses",classes);
		  return "EditEtudiant";
		  
	  }
	  
	  
	  
	   
	  
	  
	 
	  
	  
	

}

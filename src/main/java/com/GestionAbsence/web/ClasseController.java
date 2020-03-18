package com.GestionAbsence.web;

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
import com.GestionAbsence.dao.MatiereRepository;
import com.GestionAbsence.entities.Classe;
import com.GestionAbsence.entities.Matiere;

/**
 * @author ayoub
 *
 */

@Transactional
@Controller
public class ClasseController {
	
	@Autowired
	private ClasseRepository classeRepository;
	
	@Autowired
	private MatiereRepository matiereRepository;
	
	
	
	
	  @RequestMapping ( value = "/classe", method = RequestMethod.GET) 
	  public String index (Model model,
	      @RequestParam(name = "page", defaultValue = "0") int p,
	      @RequestParam(name = "size", defaultValue = "8") int s,
	      @RequestParam(name = "motCle", defaultValue = "") String mc) {     		// on va lire le paramétre motCle et on va l'affecte à mc 
		  
		  Pageable pageable = PageRequest.of(p, s);
		  Page<Classe>pageClasses = classeRepository.chercher("%"+mc+"%",pageable);
		  model.addAttribute("listClasses", pageClasses.getContent());
		  int[] pages = new int[pageClasses.getTotalPages()]; 
		  model.addAttribute("pages", pages);
		  model.addAttribute("size", s);             
		  model.addAttribute("pageCourante", p);  							
		  model.addAttribute("motCle", mc); 	
		  return "Classe";	
	  }
	  
	  
	  @RequestMapping (value = "/UpdateClasse", method = RequestMethod.POST)
	  public String update (@Valid Classe classe, BindingResult bindingResult) {
		  
		  if (bindingResult.hasErrors())
			  return "EditClasse";
		 
		  
		  classeRepository.save(classe);
		  return "ConfirmationClasse";
	  	  }
	      

	  
	  @RequestMapping (value = "/FormClasse", method = RequestMethod.GET)
	  public String formClasse (Model model, Classe classe) {
		  model.addAttribute("classe", new Classe());
		  
		  Collection<Matiere> matieres = matiereRepository.findAll();
		  model.addAttribute("listMatieres",matieres);
	
		  return "FormClasse";
	  	  }
	  

	  @RequestMapping (value = "/SaveClasse", method = RequestMethod.POST)
	  public String save (@Valid Classe classe, BindingResult bindingResult) {
		  
		  if (bindingResult.hasErrors())
			  return "FormClasse";
		 
		  classeRepository.save(classe);
		 
		  return "ConfirmationClasse";
	  	  }
	  
	
	  @RequestMapping (value = "/DeleteClasse", method = RequestMethod.GET) 
	  public String delete (Model model, Long id) { 
		  classeRepository.deleteById(id); 
		  return "redirect:/classe";
	  
	  }
	 
	  	  
	  
	  
	  @RequestMapping (value = "/EditClasse", method = RequestMethod.GET)
	  public String EditClasse (Model model, Long id) {
		  Classe c = classeRepository.getOne(id);
		  model.addAttribute("classe", c);
		  return "EditClasse";
		  
	  }
	  
	
	

}

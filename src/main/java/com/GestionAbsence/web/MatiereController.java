package com.GestionAbsence.web;

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

import com.GestionAbsence.dao.MatiereRepository;
import com.GestionAbsence.entities.Matiere;

@Transactional
@Controller
public class MatiereController {
	
	@Autowired
	private MatiereRepository matiereRepository;
	
	  @RequestMapping ( value = "/matiere", method = RequestMethod.GET) 
	  public String index (Model model,
	      @RequestParam(name = "page", defaultValue = "0") int p,
	      @RequestParam(name = "size", defaultValue = "8") int s,
	      @RequestParam(name = "motCle", defaultValue = "") String mc) {     		// on va lire le paramétre motCle et on va l'affecte à mc 
		  
		  Pageable pageable = PageRequest.of(p, s);
		  Page<Matiere>pageMatieres = matiereRepository.chercher("%"+mc+"%", pageable);
		  model.addAttribute("listMatieres", pageMatieres.getContent());
		  int[] pages = new int[pageMatieres.getTotalPages()]; 
		  model.addAttribute("pages", pages);
		  model.addAttribute("size", s);             
		  model.addAttribute("pageCourante", p);  							
		  model.addAttribute("motCle", mc); 	
		  return "Matiere";	
	  }
	  
	  @RequestMapping (value = "/FormMatiere", method = RequestMethod.GET)
	  public String formMatiere (Model model) {
		  model.addAttribute("matiere", new Matiere());
		  
		  return "FormMatiere";
	  	  }
	  
	  
	  @RequestMapping (value = "/SaveMatiere", method = RequestMethod.POST)
	  public String save (@Valid Matiere matiere, BindingResult bindingResult) {
		  
		  if (bindingResult.hasErrors())
			  return "FormMatiere";
		 
		  matiereRepository.save(matiere);
		  return "ConfirmationMatiere";
	  	  }
	  
	  
	  @RequestMapping (value = "/EditMatiere", method = RequestMethod.GET)
	  public String EditMatiere (Model model, Long id) {
		  Matiere m = matiereRepository.getOne(id);
		  model.addAttribute("matiere", m);
		  return "EditMatiere";
		  
	  }
	  
	  
	  @RequestMapping (value = "/DeleteMatiere", method = RequestMethod.GET) 
	  public String delete (Model model, Long id) { 
		  matiereRepository.deleteById(id); 
		  return "redirect:/matiere";
	  
	  }

}

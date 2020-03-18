package com.GestionAbsence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class GestionAbsenceApplication {
	
	/*
	 * @Autowired
	 * 
	 * private EtudiantRepository etudiantRepository;
	 */

	public static void main(String[] args) {
	      
		
		
		
		
		  
		  ApplicationContext ctx = SpringApplication.run(GestionAbsenceApplication.class, args);
		
//		  EtudiantRepository etudiantRepository =
//		  ctx.getBean(EtudiantRepository.class);
//		  
//		  etudiantRepository.save(new
//		  Etudiant("belgacem","ayoub","bel.ayoub@gmail.com", LocalDate.of(2019, 10,
//		  10),null)); etudiantRepository.save(new
//		  Etudiant("zaghdoudi","baha","baha.zagh@gmail.com", LocalDate.of(2019, 10,
//		  10), null)); etudiantRepository.save(new
//		  Etudiant("etud1","etud1","etud1e@gmail.com", LocalDate.of(2019, 10, 10),
//		  null)); etudiantRepository.save(new
//		  Etudiant("etu2","etud2","etud2@gmail.com", LocalDate.of(2019, 10, 10),
//		  null)); etudiantRepository.save(new
//		  Etudiant("etud3","etud3","etud3@gmail.com", LocalDate.of(2019, 10, 10),
//		  null)); etudiantRepository.save(new
//		  Etudiant("etud4","etud4","etud4@gmail.com", LocalDate.of(2019, 10, 10),
//		  null));
//		  
//		  System.out.println(etudiantRepository);
//		  
//		  ClasseRepository classeRepository = ctx.getBean(ClasseRepository.class);
//		  
//		  classeRepository.save(new Classe("Glsi", "genie logiciel",null));
//		  classeRepository.save(new Classe("Web", "dev web",null));
//		  classeRepository.save(new Classe("S", "securité",null));
//		  classeRepository.save(new Classe("Ds", "data science",null));
//		  classeRepository.save(new Classe("Rx", "reseau",null));
//		  classeRepository.save(new Classe("BI", "business intelligence",null));
//		  
//		  System.out.println(classeRepository);
		  
		 
		  
		 
		
		
		
	}
	
	

}

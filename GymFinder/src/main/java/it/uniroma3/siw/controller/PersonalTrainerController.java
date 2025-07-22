package it.uniroma3.siw.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import it.uniroma3.siw.model.ImageEntity;
import it.uniroma3.siw.model.PersonalTrainer;
import it.uniroma3.siw.service.ImageEntityService;
import it.uniroma3.siw.service.PalestraService;
import it.uniroma3.siw.service.PersonalTrainerService;
import jakarta.validation.Valid;

@Controller
public class PersonalTrainerController {
	@Autowired
	private PersonalTrainerService personalTrainerService;
	
	@Autowired
	private PalestraService palestraService;
	
	@Autowired
	private ImageEntityService imageEntityService;
	
	@GetMapping("/personalTrainers")
	public String listapersonalTrainers(Model model) {
		model.addAttribute("personalTrainers", personalTrainerService.findAll());
		return "personalTrainers";
	}
	
	@GetMapping("/personalTrainer/{id}")
	public String mostraPersonalTrainer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("personalTrainer", personalTrainerService.findById(id));
		model.addAttribute("palestre", personalTrainerService.findById(id).getPalestre());
		return "personalTrainer";
	}
	
	@GetMapping(value="/admin/indexPersonalTrainer")
	public String mostraIndexPersonalTrainer(Model model) {
		model.addAttribute("personalTrainers", personalTrainerService.findAll());
		return "admin/indexPersonalTrainer";
	}
	
	@GetMapping(value="/admin/formNewPersonalTrainer")
	public String formNewChef(Model model) {
		model.addAttribute("personalTrainer", new PersonalTrainer());
		model.addAttribute("palestre", palestraService.findAll());
		return "admin/formNewPersonalTrainer";
	}
	//salva il Personal Trainer con foto
	@PostMapping(value="/admin/formNewPersonalTrainer")
	public String salvaPersonalTrainer(@Valid @ModelAttribute("personalTrainer") PersonalTrainer personalTrainer, BindingResult bindingResult, @RequestParam("fotoFile") MultipartFile fotoFile, Model model) {
		if(bindingResult.hasErrors()) {
			return "admin/formNewPersonalTrainer";
		}
		try {
			if(!fotoFile.isEmpty()) {
				String name=UUID.randomUUID().toString() + "_" + fotoFile.getOriginalFilename();
				ImageEntity photo= new ImageEntity(name);
				this.imageEntityService.savePhysicalImage(fotoFile.getBytes(), name);
				personalTrainer.setFoto(photo);
				personalTrainerService.save(personalTrainer);
				return "redirect:/personalTrainers";
			}
			model.addAttribute("personalTrainers", personalTrainerService.findAll());
			return "admin/formNewPersonalTrainer";
			
		} catch(Exception e) {
			model.addAttribute("msgError", "Errore nel salvataggio del Personal Trainer:\n"+ e.toString());
            return "admin/formNewPersonalTrainer";
			
		}
	}
	
	@GetMapping(value="/admin/formUpdatePersonalTrainer/{id}")
	public String formUpdatePersonalTrainer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("personalTrainer", personalTrainerService.findById(id));
		return "admin/formUpdatePersonalTrainer";
	}
	
	@PostMapping(value="/admin/formUpdatePersonalTrainer/{id}")
	public String updatePersonalTrainer(@PathVariable("id") Long id,@Valid @ModelAttribute("chef") PersonalTrainer personalTrainer, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "admin/formUpdatePersonalTrainer";
		}
		//mantieni la foto di prima
		PersonalTrainer personalTrainerEsistente=personalTrainerService.findById(id);
		personalTrainer.setFoto(personalTrainerEsistente.getFoto());
		personalTrainerService.save(personalTrainer);
		return "redirect:/personalTrainer/"+personalTrainer.getId();
	}
	
	@PostMapping(value="/admin/rimuoviPersonalTrainer/{id}")
	public String rimuoviPersonalTrainer(@PathVariable("id") Long id) {
		personalTrainerService.deletePersonalTrainer(id);
		return "redirect:/personalTrainers";
	}
	
	@GetMapping(value="/admin/dettagliPersonalTrainer/{id}")
	public String mostraDettagliPersonalTrainer(@PathVariable("id") Long id, Model model) {
		model.addAttribute("personalTrainer", personalTrainerService.findById(id));
		return "admin/dettagliPersonalTrainer";
	}
}

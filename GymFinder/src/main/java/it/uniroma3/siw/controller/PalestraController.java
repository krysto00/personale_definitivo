package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.ImageEntityService;
import it.uniroma3.siw.service.PalestraService;
import it.uniroma3.siw.service.PersonalTrainerService;

@Controller
public class PalestraController {
	
	@Autowired
	private PalestraService palestraService;
	
	@Autowired
	private PersonalTrainerService personalTrainerService;
	@Autowired
	private ImageEntityService imageEntityService;
	
	//lista palestre
	@GetMapping("/palestre")
	public String mostraPalestre(Model model) {
		model.addAttribute("palestre", palestraService.findAll());
		return "palestre";
		
	}
	
	//dettagli palestre
	@GetMapping("/palestra/{id}")
	public String mostraPalestra(@PathVariable("id") Long id,Model model) {
		model.addAttribute("palestra", palestraService.findById(id));
		return "palestra";
	}

}

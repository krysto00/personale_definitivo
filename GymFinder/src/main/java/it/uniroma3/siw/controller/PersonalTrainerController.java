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
import it.uniroma3.siw.service.ImageEntityService;
import it.uniroma3.siw.service.PalestraService;
import it.uniroma3.siw.service.PersonalTrainerService;
import jakarta.validation.Valid;

@Controller
public class PersonalTrainerController {
	@Autowired
	private PersonalTrainerService personalTrainerService;
	
	@Autowired
	private PalestraService palestraeService;
	
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
}

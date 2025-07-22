package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.service.PalestraService;
import it.uniroma3.siw.service.PersonalTrainerService;

@Controller
public class MainController {
	    @Autowired
	    private PersonalTrainerService personalTrainerService;

	    @Autowired
	    private PalestraService palestraService;

	    @GetMapping("/search")
	    public String search(@RequestParam("parola") String parola, Model model) {
	        model.addAttribute("parola", parola);

	        model.addAttribute("personalTrainers", personalTrainerService.findByNomeOrCognome(parola));
	        model.addAttribute("palestre", palestraService.findByNomeOrCitta(parola));

	        return "searchResults";
	    }
	


}

package it.uniroma3.siw.controller;

import java.util.HashSet;
import java.util.Set;
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
import it.uniroma3.siw.model.Palestra;
import it.uniroma3.siw.service.ImageEntityService;
import it.uniroma3.siw.service.PalestraService;
import it.uniroma3.siw.service.PersonalTrainerService;
import jakarta.validation.Valid;

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
	
	@GetMapping(value="/admin/indexPalestra")
	public String mostraIndexPalestra(Model model) {
		model.addAttribute("palestre", palestraService.findAll());
		return "admin/indexPalestra";
	}
	
	@GetMapping(value="/admin/dettagliPalestra/{id}")
	public String mostraDettagliPalestra(@PathVariable("id") Long id, Model model) {
		model.addAttribute("palestra", palestraService.findById(id));
		return "admin/dettagliPalestra";
	}
	
	@GetMapping(value="/admin/formNewPalestra")
	public String formNewPalestra(Model model) {
		model.addAttribute("palestra", new Palestra());
		model.addAttribute("personalTrainers", personalTrainerService.findAll());
		return "admin/formNewPalestra";
	}
	
	@PostMapping(value="/admin/formNewPalestra")
	public String salvaPalestra(@Valid @ModelAttribute("palestra") Palestra palestra, BindingResult bindingResult, 
			@RequestParam("imageFiles") MultipartFile[] imageFiles, Model model) {
		//controllo errori
		if(bindingResult.hasErrors()) {
			return "admin/formNewPalestra";
		}
		try {
			//gestione immagini
			Set<ImageEntity> images= new HashSet<ImageEntity>();
			for(MultipartFile file : imageFiles) {
				if(!file.isEmpty()) {
					String name= UUID.randomUUID().toString()+ '_' + file.getOriginalFilename();
					ImageEntity img=new ImageEntity(name);
					this.imageEntityService.savePhysicalImage(file.getBytes(), name);
					images.add(img);
					
				}
			}
			palestra.setImages(images);
			palestraService.save(palestra);
			model.addAttribute("palestre", palestraService.findAll());
			return "redirect:/palestre";
			
			
		} catch(Exception e){
			model.addAttribute("msgError", "Errore nel salvataggio della palestra:\n"+ e.toString());
            return "admin/formNewPalestra";
		}
	}
	
	@GetMapping(value="/admin/formUpdatePalestra/{id}")
	public String formUpdatePalestra(@PathVariable("id") Long id, Model model) {
		model.addAttribute("palestra", palestraService.findById(id));
		return "admin/formUpdatePalestra";
		
	}
	
	@PostMapping(value="/admin/formUpdatePalestra/{id}")
	public String salvaModificaPalestra(@PathVariable("id") Long id,@Valid @ModelAttribute("palestra") Palestra palestra, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "admin/formUpdatePalestra";
		}
		Palestra palestraEsistente = palestraService.findById(id);

	    // Mantieni le immagini già salvate
		palestra.setImages(palestraEsistente.getImages());
		palestra.setPersonalTrainers(palestraEsistente.getPersonalTrainers());
		palestraService.save(palestra);
		return "redirect:/palestra/" +palestra.getId();
	}
	
	@PostMapping(value="/admin/rimuoviPalestra/{id}")
	public String rimuoviPalestra(@PathVariable("id") Long id) {
		palestraService.deletePalestra(id);
		return "redirect:/palestre";
		
	}
}

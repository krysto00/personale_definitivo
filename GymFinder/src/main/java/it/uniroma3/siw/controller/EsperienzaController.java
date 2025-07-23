package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Esperienza;
import it.uniroma3.siw.model.Palestra;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.EsperienzaService;
import it.uniroma3.siw.service.PalestraService;
import jakarta.validation.Valid;

@Controller
public class EsperienzaController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private PalestraService palestraService;
	
	@Autowired
	private EsperienzaService esperienzaService;
	
		@GetMapping("/user/formNewEsperienza/{palestra_id}")
		public String mostraFormEsperienza(@PathVariable("palestra_id") Long palestra_id, Model model, Authentication authentication) {
			
			Palestra palestra=palestraService.findById(palestra_id);
			//prendi l'utente in questione
			Credentials credentials=credentialsService.getCredentials(authentication.getName());
			User user=credentials.getUser();
					
			if(esperienzaService.hasUserReviewsPalestra(user, palestra)) {
				return "palestra/"+palestra_id;
			}
			
			Esperienza esperienza=new Esperienza();
			esperienza.setPalestra(palestra);
			model.addAttribute("esperienza", esperienza);
			model.addAttribute("palestra", palestra);
			return "user/formNewEsperienza";
			
		}
		
		@PostMapping("/esperienze/formNewEsperienza/{palestra_id}")
		public String salvaEsperienza(@PathVariable("palestra_id") Long palestra_id,
				@Valid @ModelAttribute("esperienza") Esperienza esperienza,BindingResult bindingResult,Authentication authentication,Model model) {
			
			Palestra palestra=palestraService.findById(palestra_id);
			Credentials credentials=credentialsService.getCredentials(authentication.getName());
			User user=credentials.getUser();
			
			if(bindingResult.hasErrors()) {
			    model.addAttribute("palestra", palestra);
			    return "user/formNewEsperienza";
			}

			
			esperienza.setPalestra(palestra);
			esperienza.setUser(user);
			
			esperienzaService.save(esperienza);
			
			return "redirect:/palestra/"+esperienza.getPalestra().getId();
		}

		@PostMapping("/admin/esperienze/{id}/rimuovi")
		public String rimuoviEsperienza(@PathVariable("id") Long id) {
			Esperienza esperienza=esperienzaService.findById(id);
			Long palestraId=esperienza.getPalestra().getId();
			esperienzaService.deleteEsperienza(id);
			return "redirect:/palestra/" +palestraId;
		}

}

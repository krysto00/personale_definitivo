package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.EsperienzaService;
import it.uniroma3.siw.service.PalestraService;

public class EsperienzaController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private PalestraService ristoranteService;
	
	@Autowired
	private EsperienzaService esperienzaService;

}

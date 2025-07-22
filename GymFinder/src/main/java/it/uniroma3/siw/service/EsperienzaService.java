package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Esperienza;
import it.uniroma3.siw.model.Palestra;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.EsperienzaRepository;

@Service
public class EsperienzaService {
	@Autowired
	private EsperienzaRepository esperienzaRepository;

	public boolean hasUserReviewsPalestra(User user, Palestra palestra) {
		// TODO Auto-generated method stub
		return esperienzaRepository.existsByUserAndPalestra(user, palestra);
	}

	public void save( Esperienza esperienza) {
		esperienzaRepository.save(esperienza);
		
	}

	public Esperienza findById(Long id) {
		// TODO Auto-generated method stub
		return esperienzaRepository.findById(id).get();
	}

	public void deleteEsperienza(Long id) {
		// TODO Auto-generated method stub
		esperienzaRepository.deleteById(id);
	}

}

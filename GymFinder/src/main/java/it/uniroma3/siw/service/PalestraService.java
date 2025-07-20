package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Palestra;
import it.uniroma3.siw.repository.PalestraRepository;
import jakarta.validation.Valid;

@Service
public class PalestraService {
	
	@Autowired
	private PalestraRepository palestraRepository;

	public boolean existsByNomeAndCitta(String nome, String citta) {
		// TODO Auto-generated method stub
		return palestraRepository.existsByNomeAndCitta(nome,citta);
	}

	public Iterable<Palestra> findAll() {
		// TODO Auto-generated method stub
		return palestraRepository.findAll();
	}

	public Palestra findById(Long id) {
		// TODO Auto-generated method stub
		return palestraRepository.findById(id).get();
	}

	public void save( Palestra ristorante) {
		palestraRepository.save(ristorante);
		
	}

	public void deletePalestra(Long id) {
		palestraRepository.deleteById(id);
		
	}

	public List<Palestra> findByNomeOrCitta(String parola) {
		// TODO Auto-generated method stub
		return this.palestraRepository.findByNomeIgnoreCaseContainingOrCittaIgnoreCaseContaining(parola,parola);
	}

	public List<Palestra> findTop3ByOrderByIdDesc() {
		// TODO Auto-generated method stub
		return palestraRepository.findTop3ByOrderByIdDesc();
	}
	
	

}

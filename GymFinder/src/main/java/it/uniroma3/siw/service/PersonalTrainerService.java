package it.uniroma3.siw.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.PersonalTrainer;
import it.uniroma3.siw.repository.PersonalTrainerRepository;

@Service
public class PersonalTrainerService {
	
	@Autowired
	private PersonalTrainerRepository personalTrainerRepository;

	public Iterable<PersonalTrainer> findAll() {
		// TODO Auto-generated method stub
		return personalTrainerRepository.findAll();
	}
	
	public Iterable<PersonalTrainer> findById(Set<Long> id) {
		// TODO Auto-generated method stub
		return personalTrainerRepository.findAllById(id);
	}

	public PersonalTrainer findById(Long id) {
		// TODO Auto-generated method stub
		return personalTrainerRepository.findById(id).get();
	}

	public void save( PersonalTrainer personalTrainer) {
		personalTrainerRepository.save(personalTrainer);
		
	}

	public void deletePersonalTrainer(Long id) {
		personalTrainerRepository.deleteById(id);
		
	}

	public List<PersonalTrainer> findByNomeOrCognomeOrOrigine(String parola) {
		// TODO Auto-generated method stub
		return this.personalTrainerRepository.findByNomeIgnoreCaseContainingOrCognomeIgnoreCaseContaining(parola, parola);
	}
	

}

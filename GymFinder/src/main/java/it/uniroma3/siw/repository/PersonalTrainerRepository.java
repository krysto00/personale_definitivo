package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.PersonalTrainer;


public interface PersonalTrainerRepository extends CrudRepository<PersonalTrainer, Long> {

	boolean existsByNomeAndCognome(String nome, String cognome);

	List<PersonalTrainer> findByNomeIgnoreCaseContainingOrCognomeIgnoreCaseContaining(String nome,String cognome);

}

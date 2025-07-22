package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.PersonalTrainer;


public interface PersonalTrainerRepository extends CrudRepository<PersonalTrainer, Long> {

	boolean existsByNomeAndCognome(String nome, String cognome);

	List<PersonalTrainer> findByNomeIgnoreCaseContainingOrCognomeIgnoreCaseContaining(String nome,String cognome);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM palestra_personal_trainers WHERE personal_trainers_id = :personalTrainerId", nativeQuery = true)
	void removePersonalTrainerAssociations(@Param("personalTrainerId") Long personalTrainerId);

}

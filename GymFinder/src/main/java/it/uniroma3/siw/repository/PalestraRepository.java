package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Palestra;


public interface PalestraRepository extends CrudRepository<Palestra, Long> {

	boolean existsByNomeAndCitta(String nome, String citta);

	List<Palestra> findByNomeIgnoreCaseContainingOrCittaIgnoreCaseContaining(String nome, String citta);

	List<Palestra> findTop3ByOrderByIdDesc();

}

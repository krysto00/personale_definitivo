package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Esperienza;
import it.uniroma3.siw.model.Palestra;
import it.uniroma3.siw.model.User;

public interface EsperienzaRepository extends CrudRepository<Esperienza, Long> {

	boolean existsByUserAndPalestra(User user, Palestra palestra);

}

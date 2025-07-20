package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.ImageEntity;
@Repository
public interface ImageEntityRepository extends CrudRepository<ImageEntity, Long>{

}

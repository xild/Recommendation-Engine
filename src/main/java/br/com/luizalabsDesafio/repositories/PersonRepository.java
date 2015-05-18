package br.com.luizalabsDesafio.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import br.com.luizalabsDesafio.domain.Person;

/**
 * @author Luis
 * @since 13.05.2015
 */

public interface PersonRepository extends GraphRepository<Person> {
    // the "0" parameter is a workaround for a bug in SDN
	Person findByPersonId(@Param("0") Long personId);
 
    
}


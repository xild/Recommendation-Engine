package br.com.luizalabsDesafio.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import br.com.luizalabsDesafio.domain.CustomRelationshipEntity;
import br.com.luizalabsDesafio.domain.ActionRelationship;
import br.com.luizalabsDesafio.domain.Person;

/**
 * @author Luis
 * @since 13.05.2015
 */

public interface PersonRepository extends GraphRepository<Person> {
    // the "0" parameter is a workaround for a bug in SDN
	Person findByPersonId(Long personId);
	
	@Query(value = "MATCH (person {personId: {0}})-[r:VIEWED|BOUGHT|ADD_TO_CART]->() RETURN r order by r.time limit 3")
	List<ActionRelationship> findRelationShip(@Param("0") Long personId);
	
	@Query("MATCH (person {personId: {0}})-[r:VIEWED|BOUGHT|ADD_TO_CART]->() RETURN person.personId as personId, endNode(r) as product,  r.time as time, type(r) as type order by r.time DESC limit  {1} ")
	List<CustomRelationshipEntity> findLastActions(@Param("0") Long personId, @Param("1") int limit);
	
	@Query("MATCH (person {personId: {0}})-[r:VIEWED|BOUGHT|ADD_TO_CART]->() RETURN person.personId as personId, endNode(r) as product,  r.time as time, type(r) as type order  by r.time DESC")
	List<CustomRelationshipEntity> findLastActions(@Param("0") Long personId);
    
	
	@Query("MATCH (customer:Person{personId: {0}})-[r:BOUGHT]->(product:Product)	RETURN type(r) as type,  customer.personId as personId , product as product, r.time as time order by r.time DESC limit 10")
	List<CustomRelationshipEntity> lastBuys(@Param("0") Long personId);
	
	
}


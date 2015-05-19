package br.com.luizalabsDesafio.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luizalabsDesafio.domain.CustomRelationshipEntity;
import br.com.luizalabsDesafio.domain.ActionRelationship;
import br.com.luizalabsDesafio.domain.ProjectRelationshipTypes;
import br.com.luizalabsDesafio.domain.Person;
import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.repositories.PersonRepository;
import br.com.luizalabsDesafio.repositories.ProductRepository;
@Service
@Transactional
public class PersonServices {
	
	@Autowired 
	private PersonRepository personRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private Neo4jTemplate  template;

	public List<Person> findAll(int limit){
		
		List<Person> persons = new ArrayList<Person>();
		Result<Person> findAll = personRepo.findAll();
		if(findAll != null){
			persons = findAll.as(ArrayList.class);
		}
		return limit >= persons.size() ? persons.subList(0, persons.size()) : persons.subList(0, limit);
		
	}

	public void save(long personId, String name, String email) {
		
		personRepo.save(new Person(personId, name, email));
	}

	public Person findByPersonId(long personId) {
		return	personRepo.findByPersonId(personId);
	}

	public void delete(long personId) {
		
		personRepo.delete(findByPersonId(personId));
	}
	
	public void addRelationship(long personId, long productId, ProjectRelationshipTypes relType){
		Product product = productRepo.findByProductId(productId);
		Person person = personRepo.findByPersonId(personId);
		template.save(new ActionRelationship(person, product, new Date()), relType);
	}

	public List<CustomRelationshipEntity> findActions(long personId, int limit) {
		 	
		return limit == 0 ?  personRepo.findLastActions(personId) : personRepo.findLastActions(personId, limit);
		
		
	}

	public List<CustomRelationshipEntity> lastBuys(long personId) {
		return personRepo.lastBuys(personId);
	}
	
}

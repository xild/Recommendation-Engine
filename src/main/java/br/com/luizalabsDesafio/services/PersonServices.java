package br.com.luizalabsDesafio.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luizalabsDesafio.domain.MyRelationship;
import br.com.luizalabsDesafio.domain.MyRelationshipType;
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
		return personRepo.findAll().as(ArrayList.class);
	}

	public void save(long personId, String name, String email) {
		// TODO Auto-generated method stub
		
		personRepo.save(new Person(personId, name, email));
	}

	public Person findByPersonId(long personId) {
		
		return	personRepo.findByPersonId(personId);
	}

	public void delete(long personId) {
		/**TODO 
		 * handling a better way to return with "query makes no effect on graph"
		 * */
		personRepo.delete(findByPersonId(personId));
	}
	
	public void addRelationship(long personId, long productId, MyRelationshipType relType){
		Product product = productRepo.findByProductId(productId);
		Person person = personRepo.findByPersonId(personId);
		template.save(new MyRelationship(person, product, new Date()), relType);
	}
	
}

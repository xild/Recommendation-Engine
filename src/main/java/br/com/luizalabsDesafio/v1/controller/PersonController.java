package br.com.luizalabsDesafio.v1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizalabsDesafio.domain.CustomRelationshipEntity;
import br.com.luizalabsDesafio.domain.ProjectRelationshipTypes;
import br.com.luizalabsDesafio.domain.Person;
import br.com.luizalabsDesafio.services.PersonServices;

import com.jcabi.aspects.Loggable;



/**
 * @author luis vieira

 */
@RestController
@RequestMapping(value = "v1/peoples")
public class PersonController {
	
    @Autowired
    private PersonServices personService;
	private final Logger logger = LoggerFactory.getLogger(PersonController.class);

	
   
	@Loggable
    @RequestMapping(value="", params= {"personId", "name", "email"}, method = RequestMethod.POST,headers="Accept=application/json")
    @ResponseStatus( HttpStatus.CREATED )
    public  void savePeople(@RequestParam long personId,
	@RequestParam String name,
	@RequestParam String email) { 
    	personService.save(personId, name, email);
    }
	
	@Loggable
    @RequestMapping(value="/{personId}",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseStatus( HttpStatus.OK)
    public  @ResponseBody Person getPeople(@PathVariable("personId") int personId) { 
		
		return personService.findByPersonId(personId);
	}
	
	@Loggable
    @RequestMapping(value="/{personId}",method = RequestMethod.DELETE,headers="Accept=application/json")
    @ResponseStatus( HttpStatus.OK )
    public  void  removePeople(@PathVariable("personId") int personId) { 
		
    	personService.delete(personId);
    	
    }
	
	@Loggable
    @RequestMapping(value="",method = RequestMethod.GET,headers="Accept=application/json")
    public  @ResponseBody List<Person> listPeople(@RequestParam("limit")int limit) { 

    	return personService.findAll(limit);
    
	}
	/** *$ curl -X POST http://<host>:<port>/people/{personId}/viewed/{productId}
	 * */
	@Loggable
    @RequestMapping(value="/{personId}/viewed/{productId}",method = RequestMethod.POST,headers="Accept=application/json")
    public  @ResponseBody void addViewed(@PathVariable long personId, @PathVariable long productId) { 
			personService.addRelationship(personId, productId, ProjectRelationshipTypes.VIEWED);
    
	}
	
	@Loggable
    @RequestMapping(value="/{personId}/add-to-cart/{productId}",method = RequestMethod.POST,headers="Accept=application/json")
    public  @ResponseBody void addToCart(@PathVariable long personId, @PathVariable long productId) { 
			personService.addRelationship(personId, productId, ProjectRelationshipTypes.ADD_TO_CART);
    
	}
	
	
	@Loggable
    @RequestMapping(value="/{personId}/bought/{productId}",method = RequestMethod.POST,headers="Accept=application/json")
    public  @ResponseBody void addBuy(@PathVariable long personId, @PathVariable long productId) { 
			personService.addRelationship(personId, productId, ProjectRelationshipTypes.BOUGHT);
    
	}
	
	
	@Loggable
    @RequestMapping(value="/{personId}/actions",method = RequestMethod.GET,headers="Accept=application/json")
    public  @ResponseBody List<CustomRelationshipEntity> listActions(@PathVariable long personId, @RequestParam("limit")int limit) { 
		
		
    	return personService.findActions(personId, limit);
    
	}
	
	
	
	
	
	
}

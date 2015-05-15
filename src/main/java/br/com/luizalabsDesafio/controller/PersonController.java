package br.com.luizalabsDesafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizalabsDesafio.domain.Person;

import com.jcabi.aspects.Loggable;



/**
 * @author luis vieira
 *
 */
@RestController
@RequestMapping(value = "v1/peoples")
public class PersonController {
//    @Autowired
//    private PersonService personService;
	private final Logger logger = LoggerFactory.getLogger(PersonController.class);

	
   
	@Loggable
    @RequestMapping(value="/", params= {"personId", "name", "email"}, method = RequestMethod.POST,headers="Accept=application/json")
    @ResponseStatus( HttpStatus.CREATED )
    public  void savePeople(@RequestParam long personId,
	@RequestParam String name,
	@RequestParam String email) { 
    	System.out.println("OK");
    }
	
	@Loggable
    @RequestMapping(value="/{personId}",method = RequestMethod.GET,headers="Accept=application/json")
    public  @ResponseBody Person getPeople(@PathVariable("personId") int personId) { 
		
    	System.out.println(personId);
    	return new Person(1L, "Luis Vieira","xildhc@gmail.com");
    
	}
	
	@Loggable
    @RequestMapping(value="/{personId}",method = RequestMethod.DELETE,headers="Accept=application/json")
    @ResponseStatus( HttpStatus.OK )
    public  void  removePeople(@PathVariable("personId") int personId) { 
		
    	System.out.println(personId);
    	
    }
	
	@Loggable
    @RequestMapping(value="",method = RequestMethod.GET,headers="Accept=application/json")
    public  @ResponseBody List<Person> listPeople(@RequestParam("limit")int limit) { 

		System.out.println(limit);
    	List<Person> persons = new ArrayList<Person>();
    	Person p = new Person(1L, "xildhc@gmail.com", "Luis Vieira");
    	persons.add(p);
    	p = new Person(2L, "juliana@gmail.com", "Juliana Decina");    	
    	persons.add(p);
    	return persons;
    
	}
}

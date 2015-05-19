package br.com.luizalabsDesafio;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.luizalabsDesafio.domain.Person;
import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.services.PersonServices;
import br.com.luizalabsDesafio.services.ProductServices;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBoot.class)
@WebAppConfiguration

public class PersonControllerTest  {
	
	
	private static final String VERSION = "/v1";
	private static final String PEOPLES = "/peoples";
	
	

    @Autowired
    private WebApplicationContext context;
    
   
    private PersonServices personServices;
    
    
    List<Person> persons;
    
    @Before
    public void setUp() {
    	
    	
        RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        org.mockito.MockitoAnnotations.initMocks(this);
        personServices = org.mockito.Mockito.spy(PersonServices.class);
        persons = new ArrayList<Person>();
    	
    	Random ran = new Random();
    	persons.add(new Person(new Long(ran.nextInt(1000) +1), "Luis Vieira", "luisvieira@gmail.com"));
     	persons.add(new Person(new Long(ran.nextInt(1000) +1), "Juliana Decina", "julianadecina@yahoo.com"));
     	persons.add(new Person(new Long(ran.nextInt(1000) +1), "Sueli Plaza", "sueliplaza@gmail.com"));
        
    }
    
    @Test
    public void savePeople(){
    	
        Person person = persons.get(0);
        org.mockito.Mockito.doNothing().when(personServices).save(person.getPersonId(), person.getName(), person.getEmail());

       given().
        	log().
        		all().
        			param("personId", person.getPersonId()).
        			param("email", person.getEmail()).
        			param("name", person.getName()).
        				when().post(VERSION+PEOPLES).
        					then().statusCode(HttpServletResponse.SC_CREATED);
    }
    
    @Test 
    public void    getPeople() {
    	
    	
    	org.mockito.Mockito.doReturn(new Person(999L, "Luis", "luiscorreavieira@gmail.com")).when(personServices).findByPersonId(999L);
        given().
        	log().
        		all().
	        			when().get(VERSION+PEOPLES+"/"+999).
	        						then().
	        							statusCode(HttpServletResponse.SC_OK).
	        								contentType("application/json").
	        									body ("personId",  equalTo (persons.get(0).getPersonId())).
	        									body ("email", containsString("@") ).
	        									body("name", isA(String.class));
    }   
//    
//    @Test 
//    public void     saveBatch() {
//    	org.mockito.Mockito.doNothing().when(productServices).save(products);
//
//        given().
//        	log().
//        		all().
//        			contentType(ContentType.JSON).
//        				body(products).
//        					when().post(VERSION+PRODUCTS).
//        							then().statusCode(HttpServletResponse.SC_CREATED);
//    
//    
//    }
//    
//    
//    @Test 
//    public void     getProductsLimitZero() {
//    
//    	org.mockito.Mockito.doReturn(products).when(productServices).findAll(0);
//
//    	int limit = 0;
//        given().
//        	param("limit", limit).
//        		when().get(VERSION+PRODUCTS).
//        				then().
//        					statusCode(HttpServletResponse.SC_OK).
//        						contentType("application/json");
//    }   
//    
//    @Test 
//    public void   getProductsLimit() {
//    	int limit = 3;
//        
//    	org.mockito.Mockito.doReturn(products).when(productServices).findAll(limit);
//
//    	
//        given().
//        	log().all().
//        		param("limit", limit).
//        			when().get(VERSION+PRODUCTS).
//        				then().
//        					statusCode(HttpServletResponse.SC_OK).
//        					contentType("application/json");
//    }   
//    
//    @Test
//    public void remove(){
//    	org.mockito.Mockito.doNothing().when(productServices).delete(products.get(0).getProductId());
//    	Long productId = products.get(0).getProductId();
//        given().
//        when().
//        delete(VERSION+PRODUCTS+"/"+productId).
//                
//        then().log().all().
//                statusCode(HttpServletResponse.SC_NO_CONTENT).
//                contentType("application/json");
//    	
//    }



}

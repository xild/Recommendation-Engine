package br.com.luizalabsDesafio;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.anyLong;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.repositories.ProductRepository;
import br.com.luizalabsDesafio.services.ProductServices;
import br.com.luizalabsDesafio.v1.controller.ProductController;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ApplicationBootTest.class)
public class ProductControllerTest  {
	
	
	private static final String VERSION = "/v1";
	private static final String PRODUCTS = "/products";
	
	
    @Autowired
    private WebApplicationContext context;
    
    @Mock
    private ProductController productController;
    
    
    @Mock
    private ProductServices productServices;
    
    @Autowired
    private ProductRepository repo;
    
    List<Product> products;
	private Random ran;
    
    
    @Before
    public void setUp() {
    	 org.mockito.MockitoAnnotations.initMocks(this);
    	 
    }
    
    @Test
    public void saveProduct(){
    	

//        given().
//        	log().
//        		all().
//        			param("productId", products.get(0).getProductId()).
//        			param("price", products.get(0).getPrice()).
//        			param("name", products.get(0).getName()).
//        				when().post(VERSION+PRODUCTS).
//        					then().statusCode(HttpServletResponse.SC_CREATED);
    }
    
    @Test 
    public void     getProducts() {
    	
       
    	Product product = products.get(ran.nextInt(products.size()));
        given().
        	log().
        		all().
        			when().get(VERSION+PRODUCTS+"/"+product.getProductId()).
        						then().
        							statusCode(HttpServletResponse.SC_OK).
        								contentType("application/json").
        									body ("productId",  equalTo (product.getProductId().intValue())).
        									body ("price",  notNullValue()).
        									body("name", isA(String.class));
    }   
    
    
    @Test 
    public void     getProductsLimitZero() {
    

//    	int limit = 0;
//        given().
//        	param("limit", limit).
//        		when().get(VERSION+PRODUCTS).
//        				then().
//        					statusCode(HttpServletResponse.SC_OK).
//        						contentType("application/json");
    }   
    
    @Test 
    public void   getProductsLimit() {
//    	int limit = 3;
//        
//        given().
//        	log().all().
//        		param("limit", limit).
//        			when().get(VERSION+PRODUCTS).
//        				then().
//        					statusCode(HttpServletResponse.SC_OK).
//        					contentType("application/json");
    }   
    
    @Test
    public void remove(){
//    	org.mockito.Mockito.doReturn(products.get(0)).when(productServices).findByProductId( products.get(0).getProductId());
//
//    	Long productId = products.get(0).getProductId();
//        given().
//        when().
//        delete(VERSION+PRODUCTS+"/"+productId).
//                
//        then().log().all().
//                statusCode(HttpServletResponse.SC_NO_CONTENT);
    	
    }



}

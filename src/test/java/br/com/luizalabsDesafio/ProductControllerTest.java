package br.com.luizalabsDesafio;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.repositories.ProductRepository;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBoot.class)
@WebAppConfiguration

public class ProductControllerTest  {
	
	
	private static final String VERSION = "/v1";
	private static final String PRODUCTS = "/products";

    @Autowired
    private WebApplicationContext context;
    
    @Autowired 
    private ProductRepository product;
    
    
    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
//    @Test 
//    public void     getProducts() {
//        given().
//        when().
//                get(VERSION+PRODUCTS).
//        then().
//                statusCode(HttpServletResponse.SC_OK).
//                contentType("application/json").
//                body(equalTo("Hello world!"));
//    }   

    @Test
    public void saveProduct(){
    	Product p = new Product(1L, "notebook", new java.math.BigDecimal(10));
    	product.save(p);
    }
}

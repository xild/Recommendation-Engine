package br.com.luizalabsDesafio;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.luizalabsDesafio.domain.Product;
import br.com.luizalabsDesafio.services.ProductServices;
import br.com.luizalabsDesafio.v1.controller.ProductController;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = ApplicationBoot.class)
public class ProductControllerTest  {
	
	
	private static final String VERSION = "/v1";
	private static final String PRODUCTS = "/products";
	
	
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    
    @Mock
    private ProductController productController;
    
    
    @Mock
    private ProductServices productServices;
    
    
    List<Product> products;
    @Before
    public void setUp() {
    	
    	 mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    	 RestAssuredMockMvc.mockMvc = mockMvc;

    	 org.mockito.MockitoAnnotations.initMocks(this);

       
//        productServices =     org.mockito.Mockito.mock(ProductServices.class);
    	products = new ArrayList<Product>();
    	
    	Random ran = new Random();
    	
        products.add(new Product(new Long(ran.nextInt(100) +100), "produto 1", new BigDecimal(10)));
        products.add(new Product(new Long(ran.nextInt(100) +100), "produto 2", new BigDecimal(10)));
        products.add(new Product(new Long(ran.nextInt(100) +100), "produto 3", new BigDecimal(10))); 
        
    }
    
    @Test
    public void saveProduct(){
    	
        org.mockito.Mockito.doNothing().when(productServices).save( products.get(0).getProductId(), products.get(0).getName(), products.get(0).getPrice());

        given().
        	log().
        		all().
        			param("productId", products.get(0).getProductId()).
        			param("price", products.get(0).getPrice()).
        			param("name", products.get(0).getName()).
        				when().post(VERSION+PRODUCTS).
        					then().statusCode(HttpServletResponse.SC_CREATED);
    }
    
    @Test 
    public void     getProducts() {
    	
    	org.mockito.Mockito.doReturn(new Product(99779L, "notebook", new BigDecimal(10))).when(productController).getProducts(anyLong());
       

        given().
        	log().
        		all().
        			when().get(VERSION+PRODUCTS+"/"+99779L).
        						then().
        							statusCode(HttpServletResponse.SC_OK).
        								contentType("application/json").
        									body ("productId",  equalTo (99779L)).
        									body ("price",  notNullValue()).
        									body("name", isA(String.class));
    }   
    
    @Test 
    public void     saveBatch() {
    	org.mockito.Mockito.doNothing().when(productServices).save(products);

        given().
        	log().
        		all().
        			contentType(ContentType.JSON).
        				body(products).
        					when().post(VERSION+PRODUCTS).
        							then().statusCode(HttpServletResponse.SC_CREATED);
    
    
    }
    
    
    @Test 
    public void     getProductsLimitZero() {
    
    	org.mockito.Mockito.doReturn(products).when(productServices).findAll(0);

    	int limit = 0;
        given().
        	param("limit", limit).
        		when().get(VERSION+PRODUCTS).
        				then().
        					statusCode(HttpServletResponse.SC_OK).
        						contentType("application/json");
    }   
    
    @Test 
    public void   getProductsLimit() {
    	int limit = 3;
        
    	org.mockito.Mockito.doReturn(products).when(productServices).findAll(limit);

    	
        given().
        	log().all().
        		param("limit", limit).
        			when().get(VERSION+PRODUCTS).
        				then().
        					statusCode(HttpServletResponse.SC_OK).
        					contentType("application/json");
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

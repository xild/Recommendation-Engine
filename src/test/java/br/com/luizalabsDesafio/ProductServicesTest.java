package br.com.luizalabsDesafio;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import scala.math.BigDecimal;
import br.com.luizalabsDesafio.domain.Person;
import br.com.luizalabsDesafio.domain.Product;
//import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import br.com.luizalabsDesafio.services.ProductServices;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBoot.class)
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class ProductServicesTest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	
	@Mock
	ProductServices services;
	private List<Product> products;
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(services).build();
		products = new ArrayList<Product>();
		products.add(new Product(1L, "Notebook Acer 2014", new java.math.BigDecimal(3000)));
		products.add(new Product(2L, "Tablet Acer 2014", new java.math.BigDecimal(1400)));
		products.add(new Product(1L, "Geladeira Brastemp 2014", new java.math.BigDecimal(800)));
		products.add(new Product(1L, "Mesa retangular", new java.math.BigDecimal(200)));
		//		org.mockito.Mockito.when(services.findByPersonId(anyLong()))
//				.thenReturn(persons.get(0));
		
		
	}

	@Test
	public void save() throws Exception {
		Product product = products.get(0);
		org.mockito.Mockito
				.doNothing()
				.when(services).save(anyLong(), anyString(), any());
		services.save(product.getProductId(), product.getName(), product.getPrice());
	}

	@Test
	public void getPeople() throws Exception {
		org.mockito.Mockito.when(services.findByProductId(anyLong()))
		.thenReturn(products.get(0));
		
		Product productById = services.findByProductId(1L);
		assertEquals(products.get(0), productById);
	}
	
	@Test
	public void listPeopleByLimit() throws Exception {
		
		org.mockito.Mockito
		.doReturn(products.subList(0, 2))
		.when(services).findAll(2);
		List<Product> findAll = services.findAll(2);
		
		assertEquals(2, findAll.size());
		
		}

	@Test
	public void listPeopleByLimitZero() throws Exception{

		org.mockito.Mockito
		.doReturn(products)
		.when(services).findAll(0);
		List<Product> findAll = services.findAll(0);
		
		assertEquals(4, findAll.size());
		
	}

}	

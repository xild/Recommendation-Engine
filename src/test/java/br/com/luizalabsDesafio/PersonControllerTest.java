package br.com.luizalabsDesafio;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.com.luizalabsDesafio.domain.Person;
import br.com.luizalabsDesafio.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBoot.class)
@WebAppConfiguration
public class PersonControllerTest {

	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	private MockMvc mockMvc;

	private List<Person> personList = new ArrayList<Person>();

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

	long userId;
	private final String version = "v1";

	@Autowired
	private WebApplicationContext webApplicationContext;

	// @Autowired
	// private PersonRepository personRepository;



	@Autowired
	public void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays
				.asList(converters)
				.stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		// this.bookmarkRepository.deleteAllInBatch();
		// this.accountRepository.deleteAllInBatch();

		// this.account = accountRepository.save(new Account(userName,
		// "password"));
		// this.personList.add(bookmarkRepository.save(new Bookmark(account,
		// "http://bookmark.com/1/" + userName, "A description")));
		Person p = new Person();
		p.setEmail("xildhc@gmail.com");
		p.setPersonId(1L);
		p.setName("LUIS VIEIRA");
		this.personList.add(p);
	}

	@Test
	public void readSingleBookmark() throws Exception {
		mockMvc.perform(
				get("/" + version + "/peoples/"
						+ this.personList.get(0).getPersonId())).andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.personId",is( this.personList.get(0).getPersonId().intValue())))
				.andExpect(jsonPath("$.name", is("Luis Vieira")))
				.andExpect(jsonPath("$.email", is("xildhc@gmail.com")));
	}
	
	@Autowired Neo4jTemplate template;

	@Test @Transactional
	  public void persistedProductShouldBeRetrievableFromGraphDb() {
	    Product notebook = template.save(new Product(1L, "notebook", new BigDecimal(100)));
	    GraphRepository<Product> productRepository = template.repositoryFor(Product.class);
	    Product retrievedProduct = productRepository.findBySchemaPropertyValue("id", 1L);
	    
	    assertEquals("retrieved Product matches persisted one", notebook, retrievedProduct);
	    assertEquals("retrieved Product name matches", "notebook", retrievedProduct.getName());
	  }
	
	 @Test
	    public void createPeople() throws Exception {
//	        String person = json(new Person(1L, "luis", "xildhc@gmail.com"));
//	        mockMvc.perform(post("/"+version+"/peoples")
//	                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//	                .param("name", "luis")
//	                .param("email", "xildhc@gmail.com")
//	                .param("personId", "1")).andExpect(status().isCreated())           ;
	    }

	    protected String json(Object o) throws IOException {
	        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	        this.mappingJackson2HttpMessageConverter.write(
	                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	        return mockHttpOutputMessage.getBodyAsString();
	    }
}

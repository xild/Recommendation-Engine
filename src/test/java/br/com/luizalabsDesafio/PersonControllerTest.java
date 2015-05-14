package br.com.luizalabsDesafio;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.luizalabsDesafio.domain.Person;

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
		this.personList.add(new Person(1L, "LUIS VIEIRA", "XILDHC@GMAIL.COM"));
	}

	@Test
	public void readSingleBookmark() throws Exception {
		mockMvc.perform(
				get("/" + version + "/peoples/"
						+ this.personList.get(0).getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(
						jsonPath("$.id", is(this.personList.get(0).getId()
								.intValue())))
				.andExpect(jsonPath("$.name", is("LUIS VIEIRA")))
				.andExpect(jsonPath("$.email", is("xildhc@gmail.com")));
	}
	
	 @Test
	    public void createBookmark() throws Exception {
//	        String bookmarkJson = json(new Bookmark(
//	                this.account, "http://spring.io", "a bookmark to the best resource for Spring news and information"));
//	        this.mockMvc.perform(post("/" + userName + "/bookmarks")
//	                .contentType(contentType)
//	                .content(bookmarkJson))
//	                .andExpect(status().isCreated());
	    }

	    protected String json(Object o) throws IOException {
	        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	        this.mappingJackson2HttpMessageConverter.write(
	                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	        return mockHttpOutputMessage.getBodyAsString();
	    }
}

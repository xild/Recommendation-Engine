package br.com.luizalabsDesafio;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import br.com.luizalabsDesafio.domain.Person;
import br.com.luizalabsDesafio.repositories.PersonRepository;
import br.com.luizalabsDesafio.services.PersonServices;
import br.com.luizalabsDesafio.v1.controller.PersonController;
//import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBoot.class)
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class PeoplesControllerTest {
	public static final String URI = "/peoples";
	public static final String VERSION = "/v1";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Mock
	private PersonController personController;
	
	@Mock
	private PersonRepository repo;

	@Mock
	PersonServices services;
	private Person person;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		// mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(personController)
				.build();
		person = new Person(333L, "LUIS", "xildhc@gmail.com");
		org.mockito.Mockito.when(services.findByPersonId(anyLong()))
				.thenReturn(person);

	}

	@Test
	public void save() throws Exception {

		org.mockito.Mockito
				.doNothing()
				.when(personController)
				.savePeople(person.getPersonId(), person.getName(),
						person.getEmail());

		mockMvc.perform(
				post(VERSION + URI).param("personId", "999")
						.param("name", person.getName())
						.param("email", person.getEmail()))
				.andExpect(status().isCreated());

		verify(personController).savePeople(999L, person.getName(),
				person.getEmail());
	}

	@Test
	public void getPeople() throws Exception {
		// fail("Not yet implemented");

		org.mockito.Mockito
				.doReturn(person)
				.when(personController).getPeople(anyInt());

		mockMvc.perform(get(VERSION + URI + "/{personId}", "123"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.personId", is(333)))
				.andExpect(jsonPath("$.name", isA(String.class)))
				.andExpect(jsonPath("$.email", containsString("@")));

		verify(personController).getPeople(anyInt());

	}
	
	@Test
	public void listPeopleByLimit() throws Exception {
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		
		org.mockito.Mockito
		.doReturn(persons)
		.when(personController).listPeople(anyInt());
		
		mockMvc.perform(get(VERSION + URI )	.param("limit", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
		
		verify(personController).listPeople(anyInt());
		
		}

	@Test
	public void listPeopleByLimitZero() throws Exception{
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		persons.add(new Person(1L, "ooo", "oo@oo.com"));
		
		org.mockito.Mockito
		.doReturn(persons)
		.when(repo).findAll();
		
		mockMvc.perform(get(VERSION + URI )	.param("limit", "0"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)));
		
	}

}	

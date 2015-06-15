package br.com.luizalabsDesafio;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

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
import br.com.luizalabsDesafio.services.PersonServices;
//import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBoot.class)
@WebAppConfiguration
@ActiveProfiles(profiles = "test")
public class PersonServicesTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	

	@Mock
	PersonServices services;
	private List<Person> persons;
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(services).build();
		persons = new ArrayList<Person>();
		persons.add(new Person(1L, "LUIS", "xildhc@gmail.com"));
		persons.add(new Person(2L, "Juliana", "julianadecia@yahoo.com"));
		org.mockito.Mockito.when(services.findByPersonId(anyLong()))
				.thenReturn(persons.get(0));
		
		
	}

	@Test
	public void save() throws Exception {
		Person person = persons.get(0);
		org.mockito.Mockito
				.doNothing()
				.when(services).save(anyLong(), anyString(), anyString());
		services.save(person.getPersonId(), person.getName(), person.getEmail());
	}

	@Test
	public void getPeople() throws Exception {
		org.mockito.Mockito.when(services.findByPersonId(anyLong()))
		.thenReturn(persons.get(0));
		
		Person findByPersonId = services.findByPersonId(0L);
		assertEquals(persons.get(0), findByPersonId);
	}
	
	@Test
	public void listPeopleByLimit() throws Exception {
		
		org.mockito.Mockito
		.doReturn(persons.subList(0, 1))
		.when(services).findAll(1);
		List<Person> findAll = services.findAll(1);
		
		assertEquals(1, findAll.size());
		
		}

	@Test
	public void listPeopleByLimitZero() throws Exception{

		org.mockito.Mockito
		.doReturn(persons)
		.when(services).findAll(0);
		List<Person> findAll = services.findAll(0);
		
		assertEquals(2, findAll.size());
		
	}

}	

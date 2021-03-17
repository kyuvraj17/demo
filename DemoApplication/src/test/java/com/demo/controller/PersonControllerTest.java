package com.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.model.Person;
import com.demo.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonControllerTest {

	@InjectMocks
	private PersonController personController;

	private MockMvc mockMvc;

	@Mock
	private PersonServiceImpl personService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	@Test
	public void testCreatPerson() throws Exception {

		Person person = getPerson();
		when(personService.create(ArgumentMatchers.any())).thenReturn(person);
		this.mockMvc
				.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(convertObjectToString(person)))
				.andExpect(status().is(201)).andExpect(jsonPath("$.person.firstName", is(person.getFirstName())));
	}

	@Test
	public void testGetPersons() throws Exception {

		Person person = getPerson();
		List<Person> list = new ArrayList<>();
		list.add(person);
		when(personService.findAll()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/person").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.persons.[0].firstName", is(person.getFirstName())));
	}

	@Test
	public void testGetCount() throws Exception {

		Person person = getPerson();
		List<Person> list = new ArrayList<>();
		list.add(person);
		when(personService.count()).thenReturn(new Long(1));
		mockMvc.perform(MockMvcRequestBuilders.get("/person/count").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdatePerson() throws Exception {

		Person person = getPerson();
		when(personService.update(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(person);
		this.mockMvc
				.perform(
						put("/person/1").contentType(MediaType.APPLICATION_JSON).content(convertObjectToString(person)))
				.andExpect(status().is(200)).andExpect(jsonPath("$.person.firstName", is(person.getFirstName())));
	}

	@Test
	public void testDeleteById() throws Exception {

		this.mockMvc.perform(delete("/person/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(204));
	}

	private Person getPerson() {
		Person person = new Person();
		person.setFirstName("Raju");
		person.setSurname("K");
		return person;
	}

	public static String convertObjectToString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}

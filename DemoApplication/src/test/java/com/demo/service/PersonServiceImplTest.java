package com.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.dao.PersonRepository;
import com.demo.exception.DemoException;
import com.demo.model.Person;

public class PersonServiceImplTest {

	@InjectMocks
	private PersonServiceImpl personService;

	@Mock
	private PersonRepository repository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreatePerson() {
		Person person = getPerson();
		when(repository.save(ArgumentMatchers.any())).thenReturn(person);
		Person personResponse = personService.create(person);
		assertEquals("Raju", personResponse.getFirstName());
	}

	@Test
	public void testGetCount() {
		when(repository.count()).thenReturn(new Long(1));
		assertEquals(1, personService.count());
	}

	@Test
	public void testFindAll() {
		Person person = getPerson();
		List<Person> list = new ArrayList<>();
		list.add(person);
		when(repository.findAll()).thenReturn(list);
		List<Person> persons = personService.findAll();
		assertEquals("Kashaboina", persons.get(0).getSurname());
	}

	@Test
	public void testUpdatePerson() throws Exception {
		Person person = getPerson();
		when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(person));
		when(repository.save(ArgumentMatchers.any())).thenReturn(person);
		Person personResponse = personService.update(person, 1);
		assertEquals("Raju", personResponse.getFirstName());
	}

	@Test
	public void testDeleteById() throws Exception {
		doNothing().when(repository).deleteById(ArgumentMatchers.any());
		personService.deleteById(new Integer(1));
		verify(repository, times(1)).deleteById(1);
	}

	private Person getPerson() {
		Person person = new Person();
		person.setFirstName("Raju");
		person.setSurname("Kashaboina");
		return person;
	}
}

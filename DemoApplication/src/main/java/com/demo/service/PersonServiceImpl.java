package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonRepository;
import com.demo.exception.DemoException;
import com.demo.exception.PersonNotFoundException;
import com.demo.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person create(Person person) {
		try {
			return personRepository.save(person);
		} catch (Exception exception) {
			throw new DemoException(exception.getMessage());
		}
	}

	@Override
	public Person update(Person person, Integer personId) {
		Optional<Person> result = personRepository.findById(personId);
		logger.debug("Optional result {}", result);
		if (result.isPresent()) {
			return personRepository.save(person);
		} else {
			throw new PersonNotFoundException("Person id does not exist in the system");
		}
	}

	@Override
	public List<Person> findAll() {

		try {
			return personRepository.findAll();
		} catch (Exception exception) {
			throw new DemoException(exception.getMessage());
		}
	}

	@Override
	public Long count() {

		try {
			return personRepository.count();
		} catch (Exception exception) {
			throw new DemoException(exception.getMessage());
		}
	}

	@Override
	public void deleteById(Integer userId) {
		try {
			personRepository.deleteById(userId);
		} catch (Exception exception) {
			throw new DemoException(exception.getMessage());
		}

	}
}

package com.demo.service;

import java.util.List;

import com.demo.model.Person;

public interface PersonService {

	Person create(Person person);

	Person update(Person person, Integer personId);

	List<Person> findAll();

	Long count();

	void deleteById(Integer userId);
}

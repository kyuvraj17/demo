package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Person;
import com.demo.model.ResponseModel;
import com.demo.service.PersonService;

@RestController
public class PersonController {

	private Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@PostMapping("/person")
	public ResponseEntity<ResponseModel> createPerson(@RequestBody Person person) {
		logger.info("Create a person request {}", person);
		Person result = personService.create(person);
		ResponseModel responseModel = new ResponseModel();
		responseModel.setPerson(result);
		return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.CREATED);
	}

	@PutMapping("/person/{id}")
	public ResponseEntity<ResponseModel> updatePerson(@RequestBody Person person, @PathVariable String id)
			throws Exception {
		logger.info("Update a person request {}", person);
		Integer personId = Integer.parseInt(id);
		Person result = personService.update(person, personId);
		ResponseModel responseModel = new ResponseModel();
		responseModel.setPerson(result);
		return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	}

	@GetMapping("/person")
	public ResponseEntity<ResponseModel> getPersons() {
		logger.info("Get available persons ");
		List<Person> results = personService.findAll();
		ResponseModel responseModel = new ResponseModel();
		if (CollectionUtils.isEmpty(results)) {
			return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.NO_CONTENT);
		}
		responseModel.setPersons(results);
		return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	}

	@GetMapping("/person/count")
	public ResponseEntity<ResponseModel> personCount() {
		logger.info("Count number of persons exist in the system ");
		Long result = personService.count();
		ResponseModel responseModel = new ResponseModel();
		if (result == 0) {
			return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.NO_CONTENT);
		}
		responseModel.setCount(result);
		return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	}

	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable String id) {
		logger.info("Delete a person with id {}", id);
		Integer personId = Integer.parseInt(id);
		personService.deleteById(personId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

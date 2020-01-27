package com.org.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.persistence.model.Person;
import com.org.resources.controller.interfaces.IDeletePersonDelegate;
import com.org.service.business.interfaces.IDeletePersonService;

@RestController
@RequestMapping(value = "/personmanagement/v1")
public class DeletePersonDelegate implements IDeletePersonDelegate{

	@Autowired
	private IDeletePersonService deletePersonService;
	
	
	@Override 
	@RequestMapping( value = "/person/{person_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Person> deletePersonById(@PathVariable(value="person_id") final Integer person_id) {
		deletePersonService.deletePersonById(person_id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}
}

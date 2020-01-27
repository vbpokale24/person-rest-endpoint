package com.org.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.interfaces.IRetrievePersonDelegate;
import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.model.PersonModel;


@RestController
@RequestMapping(value = "/personmanagement/v1")
public class RetrievePersonDelegate implements IRetrievePersonDelegate{

	@Autowired
	private IRetrievePersonService retrievePersonService;
	
	@Override 
	@RequestMapping( value = "/person/{person_id}", method = RequestMethod.GET)
    public ResponseEntity<PersonModel> getPersonById(@PathVariable(value="person_id") final Integer person_id) {
		return new ResponseEntity<PersonModel>(retrievePersonService.getPersonById(person_id), HttpStatus.OK);
	}
	
	@Override 
	@RequestMapping( value = "/person", method = RequestMethod.GET)
    public ResponseEntity<PersonModel> getAllPerson() {
		return new ResponseEntity<PersonModel>(retrievePersonService.getAllPerson(), HttpStatus.OK);
	}
}

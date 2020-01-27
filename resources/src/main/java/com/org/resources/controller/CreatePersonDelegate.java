package com.org.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.interfaces.ICreatePersonDelegate;
import com.org.service.business.interfaces.ICreatePersonService;
import com.org.service.model.PersonModel;

@RestController
@RequestMapping(value = "/personmanagement/v1")
public class CreatePersonDelegate implements ICreatePersonDelegate{

	@Autowired
	 private ICreatePersonService createCustomerService;
	
	@Override 
	@RequestMapping( value = "/person", method = RequestMethod.POST)
    public ResponseEntity<PersonModel> addPerson(@RequestBody final PersonModel persons) {
		PersonModel personData  = createCustomerService.addPerson(persons);
		return new ResponseEntity<PersonModel>(personData,HttpStatus.CREATED);
	}
	
}

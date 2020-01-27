package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.org.service.model.PersonModel;

public interface IRetrievePersonDelegate {

	 public ResponseEntity<PersonModel> getPersonById( Integer id);
	 public ResponseEntity<PersonModel> getAllPerson();
	
}

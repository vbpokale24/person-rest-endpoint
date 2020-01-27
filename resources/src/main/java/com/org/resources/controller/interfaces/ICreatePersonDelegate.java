package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.org.service.model.PersonModel;

public interface ICreatePersonDelegate {
	
	public ResponseEntity<PersonModel> addPerson(@RequestBody final PersonModel persons);
	
}

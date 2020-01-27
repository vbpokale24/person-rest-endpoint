package com.org.resources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.interfaces.IPatchPersonDelegate;
import com.org.service.business.interfaces.IPatchPersonService;
import com.org.service.model.PersonModel;

@RestController
@RequestMapping(value = "/personmanagement/v1")
public class PatchPersonDelegate implements IPatchPersonDelegate{

	@Autowired
	private IPatchPersonService patchPersonService;
	
	@Override 
	@RequestMapping( value = "/person/{person_id}", method = RequestMethod.PATCH)
    public ResponseEntity<PersonModel> patchPerson(@PathVariable(value="person_id") final Integer person_id, @RequestBody final PersonModel persons) {
		PersonModel personModel = patchPersonService.patchPerson(person_id, persons);
		return new ResponseEntity<PersonModel>(personModel,HttpStatus.OK);
	}
}

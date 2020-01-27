package com.org.resources.controller.interfaces;

import org.springframework.http.ResponseEntity;

import com.org.service.model.PersonModel;

public interface IPatchPersonDelegate {

	public ResponseEntity<PersonModel> patchPerson(final Integer person_id, final PersonModel persons);
}

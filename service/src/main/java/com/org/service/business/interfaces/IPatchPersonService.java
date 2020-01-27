package com.org.service.business.interfaces;

import com.org.service.model.PersonModel;

public interface IPatchPersonService {

	public 	PersonModel patchPerson(final Integer person_id, final PersonModel persons);
}

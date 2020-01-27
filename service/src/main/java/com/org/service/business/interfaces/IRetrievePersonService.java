package com.org.service.business.interfaces;

import com.org.service.model.PersonModel;

public interface IRetrievePersonService {

	public PersonModel getPersonById( Integer id);
	
	public PersonModel getAllPerson();
}

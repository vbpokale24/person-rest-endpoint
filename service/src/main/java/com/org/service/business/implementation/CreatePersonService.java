package com.org.service.business.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.interfaces.ICreatePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;

@Service
public class CreatePersonService implements ICreatePersonService{

	@Autowired
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public PersonModel addPerson(PersonModel persons) {
		List<PersonResourceModel> personList = persons.getPerson();
		
		List<Person> allPersons = new ArrayList<>();
		for (PersonResourceModel person : personList) 
			allPersons.add(mapSourceObjectToTargetObject.mapResource(person, Person.class));
		
		List<Person> personResult = (List<Person>) personRepository.saveAll(allPersons);
		
		PersonModel personModel = new PersonModel();
		List<PersonResourceModel> personListResult = new ArrayList<>();
		for (Person person : personResult) 
			personListResult.add(mapSourceObjectToTargetObject.mapResource(person, PersonResourceModel.class));
		 personModel.setPerson(personListResult);
		 return personModel;
	}

}

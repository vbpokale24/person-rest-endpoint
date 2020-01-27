package com.org.service.business.implementation.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.business.implementation.RetrievePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.interfaces.IMessages;
import com.org.service.model.PersonModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class RetrievePersonServiceTest {

	@InjectMocks
	private RetrievePersonService retrievePersonService;
	
	@Mock
	private PersonRepository personRepository;
	
	@Mock
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;
	
	@Mock
	private IMessages messages;
	
	Person personRequestPayloadDB;
	
	PersonModel personGetPayload;
	
	@Test
	public void testGetAllPerson(){
		personRequestPayloadDB =ReadObjectFromFile.getObjectFromJson("CreatePersonPayloadForDB.json", Person.class);
		List<Person> personList =  new ArrayList<Person>();
		personList.add(personRequestPayloadDB);
		Iterable<Person> person = personList;
		when(personRepository.findAll()).thenReturn(person);
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonModel.class);
		when(mapSourceObjectToTargetObject.mapResource(Mockito.any(Person.class), Mockito.any())).thenReturn(personGetPayload.getPerson().get(0));
		assertNotNull(retrievePersonService.getAllPerson());
	}
	
	@Test(expected = BadRequestException.class)
	public void testGetPersonIDNotPresent(){
		assertNotNull(retrievePersonService.getPersonById(1));
	}
	
	@Test
	public void testGetPersonIDPresent(){
		personRequestPayloadDB=  ReadObjectFromFile.getObjectFromJson("CreatePersonPayloadForDB.json", Person.class);
		when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(personRequestPayloadDB));
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonModel.class);
		when(mapSourceObjectToTargetObject.mapResource(Mockito.any(Person.class), Mockito.any())).thenReturn(personGetPayload.getPerson().get(0));
		assertNotNull(retrievePersonService.getPersonById(1));
	}
}

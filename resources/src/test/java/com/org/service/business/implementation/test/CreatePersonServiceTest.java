package com.org.service.business.implementation.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.implementation.CreatePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class CreatePersonServiceTest {

	@InjectMocks
	private CreatePersonService createPersonService;
	
	@Mock
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;
	
	@Mock
	private PersonRepository personRepository;
	
	Person personRequestPayloadDB;
	
	PersonModel personGetPayload;
	
	@Before
	public void setUp()
	{
		personRequestPayloadDB =ReadObjectFromFile.getObjectFromJson("CreatePersonPayloadForDB.json", Person.class);
		when(mapSourceObjectToTargetObject.mapResource(Mockito.any(PersonResourceModel.class), Mockito.any())).thenReturn(personRequestPayloadDB);
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonModel.class);
		when(mapSourceObjectToTargetObject.mapResource(Mockito.any(Person.class), Mockito.any())).thenReturn(personGetPayload.getPerson().get(0));
	}
	
	@Test
	public void testCreatePersonService(){
		List<Person> personResult = new ArrayList<Person>();
		personResult.add(personRequestPayloadDB);
		when(personRepository.saveAll(Mockito.any())).thenReturn(personResult);
		PersonModel personModel = createPersonService.addPerson(personGetPayload);
		assertEquals("29", personModel.getPerson().get(0).getAge());
	}
}

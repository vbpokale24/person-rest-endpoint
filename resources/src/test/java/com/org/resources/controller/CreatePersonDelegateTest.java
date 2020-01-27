package com.org.resources.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.service.business.interfaces.ICreatePersonService;
import com.org.service.model.PersonModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class CreatePersonDelegateTest {

	@InjectMocks
	private CreatePersonDelegate createPersonDelegate;
	
	@Mock
	private ICreatePersonService createPersonService;
	
	@Test
	public void createPeopleWithSingleData(){
		PersonModel personRequestPayload =ReadObjectFromFile.getObjectFromJson("CreatePersonPayload.json", PersonModel.class);
		when(createPersonService.addPerson(personRequestPayload)).thenReturn(personRequestPayload);
		ResponseEntity<PersonModel> result = createPersonDelegate.addPerson(personRequestPayload);
		assertEquals(result.getStatusCode(), HttpStatus.CREATED);;
	}
	
	@Test
	public void createPeopleWithMultipleData(){
		PersonModel personRequestPayloadResult =ReadObjectFromFile.getObjectFromJson("CreatePersonPayloadMultiplePerson.json", PersonModel.class);
		when(createPersonService.addPerson(personRequestPayloadResult)).thenReturn(personRequestPayloadResult);
		ResponseEntity<PersonModel> result = createPersonDelegate.addPerson(personRequestPayloadResult);
		assertEquals(result.getStatusCode(), HttpStatus.CREATED);
		assertEquals(result.getBody().getPerson().size(), personRequestPayloadResult.getPerson().size());
	}
}

package com.org.resources.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.service.business.interfaces.IPatchPersonService;
import com.org.service.model.PersonModel;
import com.org.testHelper.ReadObjectFromFile;

@RunWith(SpringRunner.class)
public class PatchPersonDelegateTest {

	@InjectMocks
	private PatchPersonDelegate patchPersonDelegate;
	
	@Mock
	private IPatchPersonService patchPersonService;
	
	PersonModel personGetPayload;
	@Mock
	PersonModel persons;
	@Before
	public void setUp(){
		personGetPayload =ReadObjectFromFile.getObjectFromJson("GetPersonPayload.json", PersonModel.class);
	}
	
	@Test
	public void testPatchDelegate(){
		when(patchPersonService.patchPerson(Mockito.anyInt(), Mockito.any(PersonModel.class))).thenReturn(personGetPayload);
		ResponseEntity<PersonModel>  person = patchPersonDelegate.patchPerson(1,persons);
		assertEquals(person.getStatusCode(), HttpStatus.OK);
		assertEquals(person.getBody().getPerson().get(0).getAge(),"29");
	}
	
}

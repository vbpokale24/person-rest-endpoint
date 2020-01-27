package com.org.service.interfaces.implemetation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.org.persistence.model.Person;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.interfaces.IMessages;
import com.org.service.interfaces.IPatchPersonValidator;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;

@Component
public class PatchPersonValidator implements IPatchPersonValidator{

	
	@Autowired
	private IMessages messages;
	
	@Override
	public boolean supports(Class<?> clazz) {

		return PersonModel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PersonModel person = (PersonModel) target;
		if(person.getPerson().isEmpty()){
			throw new BadRequestException(BadRequestException.NO_PERSON_PRESENT_IN_REQUEST,null, messages, "");
		}
		if(person.getPerson().size()>1){
			throw new BadRequestException(BadRequestException.MORE_THAN_ONE_PERSON_PRESENT_IN_REQUEST,null, messages, "");
		}
		
		PersonResourceModel personResource = person.getPerson().get(0);
		if(personResource.getAge()== null && personResource.getFavouriteColor()==null && personResource.getFirstName() == null && personResource.getHobby().size()==0 && personResource.getLastName() == null){
			throw new BadRequestException(BadRequestException.NO_PERSON_PRESENT_IN_REQUEST,null, messages, "");
		}
	}
	
	public boolean  validateRequestWithDBObject(PersonModel requestJson, Person dbJson){
		PersonResourceModel personResource =  requestJson.getPerson().get(0);
		
		if(personResource.getAge().equals(dbJson.getAge()) && personResource.getFavouriteColor().equals(dbJson.getFavouriteColor()) && personResource.getFirstName().equals(dbJson.getFirstName()) && personResource.getLastName().equals(dbJson.getLastName()) && personResource.getHobby().equals(dbJson.getHobby())){
			return true;
		}else{
			return false;
		}

	}
}

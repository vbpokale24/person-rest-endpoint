package com.org.service.business.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

import com.org.persistence.model.Person;
import com.org.persistence.repository.PersonRepository;
import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.business.interfaces.IPatchPersonService;
import com.org.service.business.interfaces.IRetrievePersonService;
import com.org.service.business.utils.MapSourceObjectToTargetObject;
import com.org.service.interfaces.IMessages;
import com.org.service.interfaces.IPatchPersonValidator;
import com.org.service.model.PersonModel;
import com.org.service.model.PersonResourceModel;

@Service
public class PatchPersonService implements IPatchPersonService {

	@Autowired
	private IRetrievePersonService retrievePersonService;

	@Autowired
	private IPatchPersonValidator patchPersonValidator;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	private MapSourceObjectToTargetObject mapSourceObjectToTargetObject;

	@Autowired
	private IMessages messages;

	@Override
	public PersonModel patchPerson(final Integer person_id, final PersonModel persons) {
		BindingResult result = new BeanPropertyBindingResult(persons, "person");
		ValidationUtils.invokeValidator(patchPersonValidator, persons, result);
		PersonModel personModel = retrievePersonService.getPersonById(person_id);
		if (personModel != null) {
			List<PersonResourceModel> personList = persons.getPerson();
			PersonResourceModel patchResourceModel = personList.get(0);
			patchResourceModel.setId(person_id);
			Person patchPersonModel = mapSourceObjectToTargetObject.mapResource(patchResourceModel, Person.class);
			Person personResult = personRepository.save(patchPersonModel);

			PersonModel personModelResult = new PersonModel();
			List<PersonResourceModel> personListResult = new ArrayList<>();
			personListResult.add(mapSourceObjectToTargetObject.mapResource(personResult, PersonResourceModel.class));
			personModelResult.setPerson(personListResult);
			return personModelResult;
		} else {
			throw new BadRequestException(BadRequestException.NO_PERSON_PRESENT_IN_REQUEST, null, messages, "");
		}

	}

}

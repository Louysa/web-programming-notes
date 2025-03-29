package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.PersonApplication;

import com.example.demo.model.PersonApplication;
import com.example.demo.model.Person;

@Component
public class AgeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PersonApplication.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Validate the person's age based on the date of birth.
		// - Retrieve the number of existing errors associated with the "dateOfBirth" field.
		// - If there are already errors (e.g., invalid format, future date), skip further 
		//   validation to avoid redundant or conflicting messages.
		// - If there are no existing errors, check whether the person’s age is below 15.
		// - If the person is younger than 15, reject the value.
		PersonApplication personApplication = (PersonApplication) target;
		Person p = new Person(personApplication);
		int errorCount = errors.getFieldErrorCount("dateOfBirth");
		if (errorCount == 0 && p.getAge() < 15)
			errors.rejectValue("dateOfBirth", "my.custom.err");
	}

}

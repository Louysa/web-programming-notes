package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;

public class Person extends PersonApplication {

	public Person() {

	}

	public Person(PersonApplication data) {
		super(data.getFullName(), data.getEmail(), data.getDateOfBirth(), data.getEmploymentStatus(),
				data.getEducationLevel(), data.getYearsOfExperience(), data.getSkills());
	}

	public int getAge() {
		if (super.getDateOfBirth() == null)
			return 0;

		return Period.between(super.getDateOfBirth(), LocalDate.now()).getYears();
	}
}

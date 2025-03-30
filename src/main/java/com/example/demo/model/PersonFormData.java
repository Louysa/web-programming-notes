package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class PersonFormData {
    @Size(min = 3, max = 50)
	private String firstName;

	@Size(min = 3, max = 50)
	private String lastName;

	@Past
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;

	public PersonFormData() {

	}

	public PersonFormData(String firstName, String lastName, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}

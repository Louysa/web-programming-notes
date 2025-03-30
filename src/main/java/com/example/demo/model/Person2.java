package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;

public class Person2 extends PersonFormData{
    private Long id;
	
	public Person2() {
		
	}
	
	public Person2(PersonFormData data) {
		super(data.getFirstName(), data.getLastName(), data.getDateOfBirth());
	}
	
	public int getAge() {
		if (super.getDateOfBirth() == null)
			return 0;

		return Period.between(super.getDateOfBirth(), LocalDate.now()).getYears();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "phone_number")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 10, max = 10)
	private String number;

	@ManyToOne
	@JoinColumn(name = "personid")
	private Person3 person3;

	public Phone() {
		
	}
	
	public Phone(Person3 person3) {
		this.person3 = person3;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person3 getPerson() {
		return this.person3;
	}

	public void setPerson(Person3 person3) {
		this.person3 = person3;
	}

}

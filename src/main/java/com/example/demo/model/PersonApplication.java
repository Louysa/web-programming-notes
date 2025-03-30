package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;



import jakarta.validation.constraints.*;

public class PersonApplication {

    @Size(min = 5, max = 20)
	private String fullName;
	@Email
	private String email;
	@Past
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dateOfBirth;
	@NotBlank
	private String employmentStatus;
	private String educationLevel;
	@Min(value = 0)
	private Integer yearsOfExperience;
	@NotEmpty
	private List<String> skills;

    public PersonApplication() {

	}

	public PersonApplication(String fullName, String email, LocalDate dateOfBirth, String employmentStatus,
			String educationLevel, Integer yearsOfExperience, List<String> skills) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.employmentStatus = employmentStatus;
		this.educationLevel = educationLevel;
		this.yearsOfExperience = yearsOfExperience;
		this.skills = skills;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	

}

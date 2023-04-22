package org.projetjee.athletes;

import java.sql.Date;

public class Athlete {

	private String firstName;
	private String lastName;
	private Date birthDate;
	private AthleteGender gender;
	private String nationality;
	private String disciplineName;
	
	public Athlete(String firstName, String lastName, Date birthDate, AthleteGender gender, String nationality,
			String disciplineName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.nationality = nationality;
		this.disciplineName = disciplineName;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender.name();
	}

	public void setGender(String gender) {
		this.gender = AthleteGender.valueOf(gender);
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDisciplineName() {
		return disciplineName;
	}

	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}

}

package com.teachers;

import com.user.user;

public class teacher extends user {
	private int id;
	private String specialization;
	private String birthday;
	private String firstName;
	private String lastName;
	private String telephone;
	private String address;

	public teacher(int id, String specialization, String birthday, String lastName, String telephone, String address,
			String firstName) {

		this.id = id;
		this.specialization = specialization;
		this.birthday = birthday;
		this.lastName = lastName;
		this.telephone = telephone;
		this.address = address;
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public String getSpecialization() {
		return specialization;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}

package com.students;

import com.user.user;

public class student extends user {
	private int id;
	private int className;
	private String birthDay;
	private String FirstName;
	private String LastName;
	private String Telephone;
	private String address;

	public student(int id, int className, String birthDay, String firstName, String lastName, String telephone,
			String address) {
		super();
		this.id = id;
		this.className = className;
		this.birthDay = birthDay;
		FirstName = firstName;
		LastName = lastName;
		Telephone = telephone;
		this.address = address;
	}

	public student() {

	}

	public int getId() {
		return id;
	}

	public int getClassName() {
		return className;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getTelephone() {
		return Telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setClassName(int className) {
		this.className = className;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

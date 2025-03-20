package com.user;

public class user {
	protected int id;
	protected String username;
	protected String password;
	protected String email;
	protected String role;
	protected String image;

	user(int id, String username, String password, String email, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;

	}

	public user() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}

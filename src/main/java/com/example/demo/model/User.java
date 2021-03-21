package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

	@NotNull
	private int user_id;
	@NotNull
	@NotBlank(message = "User Name Alanı Doldurulmalıdır")
	private String user_name;
	@NotNull
	private String user_surname;
	@NotNull
	@NotBlank(message = "User Email Alanı Doldurulmalıdır")
	private String user_email;
	@NotNull
	@NotBlank(message = "User Password Alanı Doldurulmalıdır")
	private String user_password;
	@NotNull
	private String user_role;

	public User() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_surname() {
		return user_surname;
	}

	public void setUser_surname(String user_surname) {
		this.user_surname = user_surname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_surname=" + user_surname
				+ ", user_email=" + user_email + ", user_password=" + user_password + ", user_role=" + user_role + "]";
	}

}

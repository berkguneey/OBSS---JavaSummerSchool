package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Author {

	@NotNull
	private int author_id;
	@NotNull
	@NotBlank(message = "Author Name Alanı Doldurulmalıdır")
	private String author_name;
	@NotNull
	private String author_surname;

	public Author() {
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_surname() {
		return author_surname;
	}

	public void setAuthor_surname(String author_surname) {
		this.author_surname = author_surname;
	}

	@Override
	public String toString() {
		return "Author [author_id=" + author_id + ", author_name=" + author_name + ", author_surname=" + author_surname
				+ "]";
	}

}

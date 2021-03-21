package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Book {

	private int book_id;

	private String book_name;
	
	private String book_src;

	private int author_id;

	private String author_name;

	private String author_surname;

	private Date book_added_date;

	public Book() {
		this.setBook_added_date(new Date());
	}

	@NotNull
	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	@NotNull
	@NotBlank(message = "Book Name Alanı Doldurulmalıdır")
	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	@NotNull
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

	public Date getBook_added_date() {
		return book_added_date;
	}

	public void setBook_added_date(Date book_added_date) {
		this.book_added_date = book_added_date;
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", book_name=" + book_name + ", author_id=" + author_id
				+ ", book_added_date=" + book_added_date + "]";
	}

	public String getBook_src() {
		return book_src;
	}

	public void setBook_src(String book_src) {
		this.book_src = book_src;
	}

}

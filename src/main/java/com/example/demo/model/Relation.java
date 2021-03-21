package com.example.demo.model;

public class Relation {

	private int user_id;
	private int book_id;
	private String book_src;
	private String book_name;
	private int relation_type;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getRelation_type() {
		return relation_type;
	}

	public void setRelation_type(int relation_type) {
		this.relation_type = relation_type;
	}

	@Override
	public String toString() {
		return "Relation [user_id=" + user_id + ", book_id=" + book_id + ", relation_type=" + relation_type + "]";
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_src() {
		return book_src;
	}

	public void setBook_src(String book_src) {
		this.book_src = book_src;
	}

}

package com.example.demo.dao;

import java.util.List;
import com.example.demo.model.Book;

public interface BookDao {

	public List<Book> getBooks();

	public List<Book> getBookByName(String bookname);

	public Book getBookById(int id);

	public Boolean insertBook(Book b);

	public Boolean updateBook(int id, Book b);

	public Boolean deleteBook(int id);

	public Boolean deleteBookByAuthorId(int author_id);
}

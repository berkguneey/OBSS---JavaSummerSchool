package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;

@Service
public interface BookService {

	public List<Book> getBooks();

	public List<Book> getBookByName(String bookname);

	public Book getBookById(int id);

	public Boolean insertBook(Book b);

	public Boolean updateBook(int id, Book b);

	public Boolean deleteBook(int id);
	
	public Boolean deleteBookByAuthorId(int author_id);
}

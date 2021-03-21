package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;

@Component
public class BookServiceImpl implements BookService {

	private BookDao bookDao;
	
	@Autowired	
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> getBooks() {
		return bookDao.getBooks();
	}
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}

	public Boolean insertBook(Book b) {
		return bookDao.insertBook(b);
	}

	public Boolean updateBook(int id, Book b) {
		return bookDao.updateBook(id, b);
	}

	public Boolean deleteBook(int id) {
		return bookDao.deleteBook(id);
	}
	public Boolean deleteBookByAuthorId(int author_id) {
		return bookDao.deleteBookByAuthorId(author_id);
	}
	@Override
	public List<Book> getBookByName(String bookname) {
		return bookDao.getBookByName(bookname);
	}
}

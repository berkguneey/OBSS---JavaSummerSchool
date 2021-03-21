package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.AuthorDao;
import com.example.demo.model.Author;

@Component
public class AuthorServiceImpl implements AuthorService {

	private AuthorDao authorDao;
	
	@Autowired
	public AuthorServiceImpl(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public List<Author> getAuthors() {
		return authorDao.getAuthors();
	}

	@Override
	public Boolean insertAuthor(Author a) {
		return authorDao.insertAuthor(a);
	}

	@Override
	public Boolean updateAuthor(int id, Author a) {
		return authorDao.updateAuthor(id, a);
	}

	@Override
	public Boolean deleteAuthor(int id) {
		return authorDao.deleteAuthor(id);
	}

	@Override
	public Author getAuthorById(int id) {
		return authorDao.getAuthorById(id);
	}

}

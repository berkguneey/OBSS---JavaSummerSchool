package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Author;

@Service
public interface AuthorService {
	
	public List<Author> getAuthors();
	
	public Author getAuthorById(int id);
	
	public Boolean insertAuthor(Author a);
	
	public Boolean updateAuthor(int id, Author a);
	
	public Boolean deleteAuthor(int id);
}

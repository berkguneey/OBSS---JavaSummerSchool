package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Author> getAuthors() {
		String sql = "SELECT * FROM author";
		List<Author> authors = new ArrayList<Author>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Author author = new Author();
			author.setAuthor_id((int) (row.get("author_id")));
			author.setAuthor_name((String) (row.get("author_name")));
			author.setAuthor_surname((String) (row.get("author_surname")));
			authors.add(author);
		}
		return authors;
	}

	@Override
	public Boolean insertAuthor(Author a) {
		String sql = "INSERT INTO author " + "(author_name, author_surname) VALUES (?, ?)";
		int result = jdbcTemplate.update(sql, new Object[] { a.getAuthor_name(), a.getAuthor_surname() });
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean updateAuthor(int id, Author a) {
		String sql = "UPDATE author SET author_name= '" + a.getAuthor_name() + "', author_surname='"
				+ a.getAuthor_surname() + "' WHERE author_id=" + id;
		int result = jdbcTemplate.update(sql);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean deleteAuthor(int id) {
		String sql = "DELETE FROM author WHERE author_id='" + id + "'";
		int result = jdbcTemplate.update(sql);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Author getAuthorById(int id) {
		String sql = "SELECT * FROM author WHERE author_id='"+(int)id+"'";
		Map<String, Object> row = null;
		try {
			row = jdbcTemplate.queryForMap(sql);
		}catch(Exception e) {
			return null;
		}
		if(row==null) {
			return null;
		}
		Author author = new Author();
		author.setAuthor_id((int) (row.get("author_id")));
		author.setAuthor_name((String) (row.get("author_name")));
		author.setAuthor_surname((String) (row.get("author_surname")));
		return author;
	}

}

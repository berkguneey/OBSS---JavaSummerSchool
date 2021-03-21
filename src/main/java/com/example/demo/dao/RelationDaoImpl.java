package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Relation;

@Component
public class RelationDaoImpl implements RelationDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RelationDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Relation> getRelations(int user_id) {
		String sql = "SELECT * FROM user_to_book_relation r LEFT JOIN book b ON (r.book_id = b.book_id) WHERE user_id='"
				+ user_id + "' ";
		List<Relation> relations = new ArrayList<Relation>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Relation relation = new Relation();
			relation.setUser_id((int) (row.get("user_id")));
			relation.setBook_id((int) (row.get("book_id")));
			relation.setBook_src((String) (row.get("book_src")));
			relation.setRelation_type((int) (row.get("relation_type")));
			relation.setBook_name((String) (row.get("book_name")));
			relations.add(relation);
		}
		return relations;
	}

	@Override
	public void addToFavourite(int book_id, int user_id) {
		String sql = "INSERT INTO user_to_book_relation " + "(book_id, user_id, relation_type) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { book_id, user_id, 1 });
	}
	
	@Override
	public boolean isUserFavourite(int book_id, int user_id) {
		String sql = "SELECT count(*) as count FROM user_to_book_relation WHERE book_id='"+book_id+"' and user_id='"+user_id+"' and relation_type="+ 1;
		Map<String, Object> result = jdbcTemplate.queryForMap(sql);
		if ((Long)(result.get("count")) >0)
			return true;
		else
			return false;
	}

	@Override
	public void addToWishList(int book_id, int user_id) {
		String sql = "INSERT INTO user_to_book_relation " + "(book_id, user_id, relation_type) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { book_id, user_id, 2 });
	}
	
	@Override
	public boolean isUserWishList(int book_id, int user_id) {
		String sql = "SELECT count(*) as count FROM user_to_book_relation WHERE book_id='"+book_id+"' and user_id='"+user_id+"' and relation_type="+ 2;
		Map<String, Object> result = jdbcTemplate.queryForMap(sql);
		if ((Long)(result.get("count")) >0)
			return true;
		else
			return false;
	}

	@Override
	public void deleteFavouriteBook(int id, int user_id) {
		String sql = "DELETE FROM user_to_book_relation WHERE book_id='" + id + "' and user_id='"+user_id+"' and relation_type=" + 1;
		jdbcTemplate.update(sql);
		
	}

	@Override
	public void deleteListBook(int id, int user_id) {
		String sql = "DELETE FROM user_to_book_relation WHERE book_id='" + id + "' and user_id='"+user_id+"' and relation_type=" + 2;
		jdbcTemplate.update(sql);
		
	}
	public Boolean deleteRelationByBookId(int book_id) {
		String sql = "DELETE FROM user_to_book_relation WHERE book_id='" + book_id + "'";
		int result = jdbcTemplate.update(sql);	
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
}

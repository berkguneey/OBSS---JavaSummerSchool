package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Relation;

public interface RelationDao {

	public List<Relation> getRelations(int user_id);

	public void addToFavourite(int book_id, int user_id);

	public void addToWishList(int book_id, int user_id);

	public boolean isUserWishList(int book_id, int user_id);
	
	public boolean isUserFavourite(int book_id, int user_id);
	
	public void deleteFavouriteBook(int id, int user_id);
	
	public void deleteListBook(int id, int user_id);
	
	public Boolean deleteRelationByBookId(int book_id);
}

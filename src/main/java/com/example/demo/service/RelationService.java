package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Relation;

@Service
public interface RelationService {
	
	public List<Relation> getRelations(int user_id);
	
	public void addToWishList(int book_id, int user_id);
	
	public void addToFavourite(int book_id, int user_id);

	public boolean isUserFavourite(int book_id, int user_id);
	
	public boolean isUserWishList(int book_id, int user_id);
	
	public void deleteFavouriteBook(int id, int user_id);
	
	public void deleteListBook(int id, int user_id);
	
	public Boolean deleteRelationByBookId(int book_id);
}

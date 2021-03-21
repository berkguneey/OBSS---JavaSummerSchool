package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.RelationDao;
import com.example.demo.model.Relation;

@Component
public class RelationServiceImpl implements RelationService {

	private RelationDao relationDao;
	
	@Autowired
	public RelationServiceImpl(RelationDao relationDao) {
		this.relationDao = relationDao;
	}

	@Override
	public List<Relation> getRelations(int user_id) {
		return relationDao.getRelations(user_id);
	}

	@Override
	public void addToWishList(int book_id, int user_id) {
		relationDao.addToWishList(book_id, user_id);

	}

	@Override
	public void addToFavourite(int book_id, int user_id) {
		relationDao.addToFavourite(book_id, user_id);

	}

	@Override
	public boolean isUserFavourite(int book_id, int user_id) {
		return relationDao.isUserFavourite(book_id, user_id);
	}

	@Override
	public boolean isUserWishList(int book_id, int user_id) {
		return relationDao.isUserWishList(book_id, user_id);
	}

	@Override
	public void deleteFavouriteBook(int id, int user_id) {
		relationDao.deleteFavouriteBook(id, user_id);
	}

	@Override
	public void deleteListBook(int id, int user_id) {
		relationDao.deleteListBook(id, user_id);
	}

	@Override
	public Boolean deleteRelationByBookId(int book_id) {
		return relationDao.deleteRelationByBookId(book_id);
	}


}

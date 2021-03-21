package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Relation;
import com.example.demo.service.RelationService;
import com.example.demo.service.UserService;

@Controller
public class RelationController {

	private RelationService relationService;
	private UserService userService;

	@Autowired
	public RelationController(RelationService relationService, UserService userService) {
		this.relationService = relationService;
		this.userService = userService;
	}

	// /books -> open books.html - lists all books
	@RequestMapping(value = { "/favourities" })
	public ModelAndView Favourities() {
		List<Relation> favourities = new ArrayList<>();
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int user_id = userService.getUserIdByEmail(email);
		model.setViewName("favourities");
		List<Relation> relations = this.relationService.getRelations(user_id);
		for (Relation r : relations) {
			if (r.getRelation_type() == 1) {
				favourities.add(r);
			}
		}
		model.addObject("favourities", favourities);
		return model;
	}

	// /books -> open books.html - lists all books
	@RequestMapping(value = { "/wishlists" })
	public ModelAndView Wishlists() {
		List<Relation> wishlists = new ArrayList<>();
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int user_id = userService.getUserIdByEmail(email);
		model.setViewName("wishlists");
		List<Relation> relations = this.relationService.getRelations(user_id);
		for (Relation r : relations) {
			if (r.getRelation_type() == 2) {
				wishlists.add(r);
			}
		}
		model.addObject("wishlists", wishlists);
		return model;
	}
}

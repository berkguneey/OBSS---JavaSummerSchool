package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = { "/users", "/admin/users" })
	public ModelAndView Users() {
		ModelAndView model = new ModelAndView();
		model.setViewName("users");
		List<User> users = this.userService.getUsers();
		model.addObject("users", users);
		return model;
	}

	// /books/add -> open add-book.html - lists authors in select box - create book
	// object
	@RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
	public ModelAndView UserAddGet() {
		ModelAndView model = new ModelAndView();
		model.setViewName("add-user");
		List<User> users = this.userService.getUsers();
		model.addObject("user", new User());
		model.addObject("users", users);
		return model;
	}

	// /books/add -> open add-book.html - lists authors in select box - fills book
	// class with form values - prints errors
	@RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)
	public ModelAndView UserAddPost(@Valid @ModelAttribute User user, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("add-book");
		List<User> authors = this.userService.getUsers();
		model.addObject("authors", authors);
		if (!result.hasErrors()) {
			this.userService.insertUser(user);
			model.setViewName("redirect:" + "/users");
		}
		return model;
	}

	// /books/delete -> delete book and go /books page
	@RequestMapping(value = "/admin/users/delete", method = RequestMethod.GET)
	public ModelAndView UserDeletePost(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		userService.deleteUser(id);
		model.setViewName("redirect:" + "/users");
		return model;
	}

	// /books/edit -> edit'lenecek objenin id'sini al, bu id'nin bulunduğu nesneyi
	// döndür
	@RequestMapping(value = "/admin/users/edit", method = RequestMethod.GET)
	public ModelAndView UserEditGet(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		List<User> users = this.userService.getUsers();
		for (User u : users) {
			if (id == u.getUser_id()) {
				model.addObject("user", u);
				model.setViewName("edit-user");
			}
		}
		return model;
	}

	// /books/edit -> update et ve /books sayfasına dön
	@RequestMapping(value = "/admin/users/edit", method = RequestMethod.POST)
	public ModelAndView BookEditPost(@RequestParam("id") int id, @Valid @ModelAttribute User user,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getUser_password());
		user.setUser_password(encodedPassword);
		this.userService.updateUser(id, user);
		model.setViewName("redirect:" + "/users");

		return model;
	}

}

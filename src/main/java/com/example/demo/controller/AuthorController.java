package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;

@Controller
public class AuthorController {

	private AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@RequestMapping(value = { "/authors", "/admin/authors" })
	public ModelAndView Authors() {
		ModelAndView model = new ModelAndView();
		model.setViewName("authors");
		List<Author> authors = this.authorService.getAuthors();
		model.addObject("authors", authors);
		return model;
	}

	// /books/add -> open add-book.html - lists authors in select box - create book
	// object
	@RequestMapping(value = "/admin/authors/add", method = RequestMethod.GET)
	public ModelAndView AuthorAddGet() {
		ModelAndView model = new ModelAndView();
		model.setViewName("add-author");
		List<Author> authors = this.authorService.getAuthors();
		model.addObject("author", new Author());
		model.addObject("authors", authors);
		return model;
	}

	// /books/add -> open add-book.html - lists authors in select box - fills book
	// class with form values - prints errors
	@RequestMapping(value = "/admin/authors/add", method = RequestMethod.POST)
	public ModelAndView AuthorAddPost(@Valid @ModelAttribute Author author, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("add-author");
		List<Author> authors = this.authorService.getAuthors();
		model.addObject("authors", authors);
		if (!result.hasErrors()) {
			this.authorService.insertAuthor(author);
			model.setViewName("redirect:" + "/authors");
		}
		return model;
	}

	// /books/delete -> delete book and go /books page
	@RequestMapping(value = "/admin/authors/delete", method = RequestMethod.GET)
	public ModelAndView BookDeletePost(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		authorService.deleteAuthor(id);
		model.setViewName("redirect:" + "/authors");
		return model;
	}

	// /books/edit -> edit'lenecek objenin id'sini al, bu id'nin bulunduğu nesneyi
	// döndür
	@RequestMapping(value = "/admin/authors/edit", method = RequestMethod.GET)
	public ModelAndView BookEditGet(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		List<Author> authors = this.authorService.getAuthors();
		for (Author a : authors) {
			if (id == a.getAuthor_id()) {
				model.addObject("author", a);
				model.setViewName("edit-author");
			}
		}
		return model;
	}

	// /books/edit -> update et ve /books sayfasına dön
	@RequestMapping(value = "/admin/authors/edit", method = RequestMethod.POST)
	public ModelAndView BookEditPost(@RequestParam("id") int id, @Valid @ModelAttribute Author author,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		this.authorService.updateAuthor(id, author);
		model.setViewName("redirect:" + "/authors");

		return model;
	}

}

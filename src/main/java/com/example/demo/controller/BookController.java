package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.RelationService;
import com.example.demo.service.UserService;

@Controller
public class BookController {

	private BookService bookService;
	private AuthorService authorService;
	private UserService userService;
	private RelationService relationService;

	@Autowired
	public BookController(BookService bookService, AuthorService authorService, UserService userService,
			RelationService relationService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.userService = userService;
		this.relationService = relationService;
	}

	@RequestMapping(value = { "/books", "/admin/books" })
	public ModelAndView Books() {
		ModelAndView model = new ModelAndView();
		model.setViewName("books");
		List<Book> books = this.bookService.getBooks();
		model.addObject("books", books);
		// bookService.getBookById(3);
		return model;
	}

	@RequestMapping(value = { "/books/search", "/admin/books/search" }, method = RequestMethod.POST)
	public ModelAndView BookSearchPost(@RequestParam("bookname") String bookname) {
		ModelAndView model = new ModelAndView();
		if (bookname != "") {
			List<Book> searches = this.bookService.getBookByName(bookname);
			model.addObject("searches", searches);
		}
		model.setViewName("searches");
		return model;
	}

	@RequestMapping(value = "/admin/books/add", method = RequestMethod.GET)
	public ModelAndView BookAddGet() {
		ModelAndView model = new ModelAndView();
		model.setViewName("add-book");
		List<Author> authors = this.authorService.getAuthors();
		model.addObject("book", new Book());
		model.addObject("authors", authors);
		return model;
	}

	@RequestMapping(value = "/admin/books/add", method = RequestMethod.POST)
	public ModelAndView BookAddPost(@Valid @ModelAttribute Book book, BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("add-book");
		List<Author> authors = this.authorService.getAuthors();
		model.addObject("authors", authors);
		if (!result.hasErrors()) {
			this.bookService.insertBook(book);
			model.setViewName("redirect:" + "/books");
		}
		return model;
	}

	@RequestMapping(value = "/admin/books/delete", method = RequestMethod.GET)
	public ModelAndView BookDeletePost(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		bookService.deleteBook(id);
		model.setViewName("redirect:" + "/books");
		return model;
	}

	@RequestMapping(value = "/admin/books/edit", method = RequestMethod.GET)
	public ModelAndView BookEditGet(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView();
		List<Book> books = this.bookService.getBooks();
		List<Author> authors = this.authorService.getAuthors();
		model.addObject("authors", authors);
		for (Book b : books) {
			if (id == b.getBook_id()) {
				model.addObject("book", b);
				model.setViewName("edit-book");
			}
		}
		return model;
	}

	@RequestMapping(value = "/admin/books/edit", method = RequestMethod.POST)
	public ModelAndView BookEditPost(@RequestParam("id") int id, @Valid @ModelAttribute Book book,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		this.bookService.updateBook(id, book);
		model.setViewName("redirect:" + "/books");

		return model;
	}

	@RequestMapping(value = "/books/addToList", method = RequestMethod.GET)
	public ModelAndView AddBookListGet(@RequestParam("id") int book_id) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int user_id = userService.getUserIdByEmail(email);
		if (!relationService.isUserWishList(book_id, user_id)) {
			relationService.addToWishList(book_id, user_id);
		}
		model.setViewName("redirect:" + "/wishlists");
		return model;
	}

	@RequestMapping(value = "/books/addToFavourite", method = RequestMethod.GET)
	public ModelAndView AddBookFavouriteGet(@RequestParam("id") int book_id) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int user_id = userService.getUserIdByEmail(email);
		if (!relationService.isUserFavourite(book_id, user_id)) {
			relationService.addToFavourite(book_id, user_id);
		}
		model.setViewName("redirect:" + "/favourities");
		return model;
	}

}

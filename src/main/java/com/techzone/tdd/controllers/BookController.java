package com.techzone.tdd.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techzone.tdd.models.Book;
import com.techzone.tdd.services.BookService;

@RestController()
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/all")
	public List<Book> getAllBooks() {
		List <Book> books = new ArrayList<>();
		books = bookService.getAllBooks();
		
		return books;
	}
}

package com.techzone.tdd.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techzone.tdd.models.Book;

@Service
public interface BookService {

	List<Book> getAllBooks();
	
}

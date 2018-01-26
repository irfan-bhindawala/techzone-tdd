package com.techzone.tdd.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.techzone.tdd.models.Book;
import com.techzone.tdd.services.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mvcMock;
	
	@MockBean
	private BookService bookService;
	
	@Test
	public void givenBookAndGetBooksShouldReturnListOfBooks() throws Exception{
		//Arrange
		Book book = new Book();
		book.setTitle("TDD-with-spring-Boot");
		book.setIsbn("978-3-16-148410-0");
		book.setAuthor("josephee");
		
		List<Book> books = new ArrayList<>();
		books.add(book);
		
		given(bookService.getAllBooks()).willReturn(books); 
		
		//act and assert
		mvcMock.perform(get("/api/books/all")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void whenBookIsNotThereThenGetAllBooksShouldReturnEmptyList() throws Exception{
		//Arrange
		given(bookService.getAllBooks()).willReturn(new ArrayList<>()); 
	
		//act and assert
		mvcMock.perform(get("/api/books/all")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
	}
}



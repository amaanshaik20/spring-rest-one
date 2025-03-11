package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CustomException;
import com.model.BookDTO;
import com.model.BookEntity;
import com.repository.BookRepository;
import com.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/books")
@Validated
public class HomeController {
	private BookService service;

	HomeController(BookService service) {
		this.service = service;
	}

	@GetMapping("/hello")
	public String greet() {
		return "Assalamualaikum";
	}

	@GetMapping
	public List<BookDTO> showAllBooks() {
		return service.getAllBooks();
	}

	@GetMapping("/{subject}")
	public List<BookDTO> showBooksBasedOnSubject(@PathVariable("subject") String subject) throws CustomException {
		List<BookDTO> bookList = service.getBooksBySubject(subject);
		if (bookList.isEmpty())
			throw new CustomException("No books found for subject " + subject);

		return bookList;
	}

	

//	@GetMapping("/search")
//	public List<BookDTO> showBooksLessThanPrice(@RequestParam("price") Double price){
//		return service.getBooksWithPriceLessThan(price);
//	}	

//	@GetMapping("/search")
//	public List<BookDTO> showBooksLessThanPrice(@RequestParam("price") Double price,
//			@RequestParam("subject") String subject) {
//		return service.getBooksWithPriceLessThan(price, subject);
//	}
	
	@GetMapping("/search")
	public List<BookDTO> showBooksLessThanPrice(
			@RequestParam("price") @Positive @Min(value = 200, message = "price below range") Double price,
			@RequestParam("subject") String subject) {
		return service.getBooksWithPriceLessThan(price, subject);
	}

	

//	@PostMapping
//	public String addNewBook(@RequestBody BookDTO book) {
//		if(service.addBook(book))
//			return "Added";
//		
//		return "Fail to add";
//	}

	@PostMapping
	public ResponseEntity<?> addNewBook(@Valid @RequestBody BookDTO book) {
		if (service.addBook(book))
			return new ResponseEntity<BookDTO>(book, HttpStatus.OK);

		return new ResponseEntity<String>("Fail to add", HttpStatus.NOT_FOUND);
	}

	@PutMapping
	public ResponseEntity<?> editBookData(@RequestBody BookDTO book) {
		if (service.editBookData(book))
			return new ResponseEntity<BookDTO>(book, HttpStatus.OK);

		return new ResponseEntity<String>("Fail to edit", HttpStatus.NOT_FOUND);
	}
	
	
	@Autowired
	BookRepository repo;
	
	@GetMapping("/allBooks")
	public Iterable<BookEntity> showAllBooksPresent(){
		Iterable<BookEntity> list=repo.findAll();
		return list;
	}
	@GetMapping("/javaBooks")
	public Iterable<BookEntity> showJavaBooksPresent(){
		Iterable<BookEntity> list=repo.findAllBySubject("java");
		return list;
	}

}

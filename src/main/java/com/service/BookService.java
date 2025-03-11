package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.BookDTO;
import com.model.BookEntity;
import com.repository.BookRepository;

@Service
public class BookService {
	private List<BookDTO> bookList = new ArrayList<>();
	
	@Autowired
	private BookRepository repo;
	public BookService() {
//		bookList.add(new BookDTO(101, "Spring In Action", 425.0, "Spring"));
//		bookList.add(new BookDTO(102, "Core Java", 315.0, "Java"));
//		bookList.add(new BookDTO(103, "Let Us C", 320.0, "Angular"));
//		bookList.add(new BookDTO(104, "Java Demystified", 400.0, "Java"));
	}
	private BookEntity convertToEntity(BookDTO book) {
		BookEntity be=new BookEntity();
		be.setTitle(book.getTitle());
		be.setPrice(book.getPrice());
		be.setSubject(book.getSubject());
		return be;
	}
 
	private BookDTO convertToDTO(BookEntity be) {
		BookDTO bdto=new BookDTO();
		bdto.setBookCode(be.getBookCode());
		bdto.setTitle(be.getTitle());
		bdto.setPrice(be.getPrice());
		bdto.setSubject(be.getSubject());
		return bdto;		
	}
	public boolean addBook(BookDTO book) {
		//to be modified to use repository
		BookEntity bookEntity=convertToEntity(book);
		
		//to persist the book data into the db table [DML]
		BookEntity bookEntity1=repo.save(bookEntity);
		
		if(bookEntity1.getBookCode()!=null) {
			System.out.println(bookEntity1.getBookCode());
			book.setBookCode(bookEntity1.getBookCode());
			return true;
		}
		return false;
		//return bookList.add(book);
	}
 
 
	public List<BookDTO> getAllBooks() {
		List<BookEntity> enlist = (List<BookEntity>) repo.findAll();
		for(BookEntity en:enlist) {
			bookList.add(convertToDTO(en));
		}
		return bookList;
	}
	

	public List<BookDTO> getBooksBySubject(String subject) {
		List<BookDTO> collect = bookList.stream().filter((b) -> b.getSubject().equalsIgnoreCase(subject))
				.collect(Collectors.toList());
		return collect;
	}

//	public List<BookDTO> getBooksWithPriceLessThan(Double price){
//		List<BookDTO> collect =bookList.stream()
//				.filter((b)->b.getPrice()<price).collect(Collectors.toList());
//		return collect;
//	}
	public List<BookDTO> getBooksWithPriceLessThan(Double price, String subjec) {
		List<BookDTO> collect = bookList.stream()
				.filter((b) -> b.getPrice() < price && b.getSubject().equalsIgnoreCase(subjec))
				.collect(Collectors.toList());
		return collect;
	}

//	public boolean addBook(BookDTO book) {
//		return bookList.add(book);
//	}

	public boolean editBookData(BookDTO book) {
		boolean flag = false;
		BookDTO book_ = null;
		for (BookDTO bk : bookList) {
			if (bk.getBookCode() == book.getBookCode()) {
				flag = true;
				book_ = bk;
				break;
			}
		}
		if (flag) {
			book_.setPrice(book.getPrice());
			book_.setTitle(book.getTitle());
			book_.setSubject(book.getSubject());
		}
		return flag;
	}

}

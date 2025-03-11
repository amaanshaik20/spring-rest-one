package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_code")
	private Integer bookCode;
	
//	private String title;
	private Double price;
	private String subject;
	public BookEntity() {
		// TODO Auto-generated constructor stub
	}
	public BookEntity(String title, Double price, String subject) {
		super();
		this.title = title;
		this.price = price;
		this.subject = subject;
	}
	public Integer getBookCode() {
		return bookCode;
	}
	public void setBookCode(Integer bookCode) {
		this.bookCode = bookCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}

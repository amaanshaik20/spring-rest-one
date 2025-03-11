package com.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookDTO {
	private Integer bookCode;
	@NotEmpty
	@Size(min=5,message="minimum 5 characters")
	private String title;
	@NotNull
	@Min(value=200)
	private Double price;
	@NotEmpty
	private String subject;
	public BookDTO(Integer bookCode, String title, Double price, String subject) {
		super();
		this.bookCode = bookCode;
		this.title = title;
		this.price = price;
		this.subject = subject;
	}
	public BookDTO() {
		// TODO Auto-generated constructor stub
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

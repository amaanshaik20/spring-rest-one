package com.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			details.add(fieldName + "|" + errorMessage);
		});		
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}
 
	@ExceptionHandler(ConstraintViolationException.class)
	public String handleCVException(ConstraintViolationException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(CustomException.class)
	public String handleCustomException(CustomException ex) {
		return ex.getMessage();
	}
}

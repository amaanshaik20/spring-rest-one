package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.BookEntity;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer>{
	Iterable<BookEntity> findAllBySubject(String string);
}

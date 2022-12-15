package com.springboot.rest.service;

import java.util.List;

import com.springboot.rest.model.Book;

public interface BookService {
	Book saveBook(Book book);
	List<Book> getAllBoooks();
	Book getBookById(long id);
	Book updateBook(Book book, long id);
	void deleteBook(long id);
}


package com.springboot.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.rest.exception.ResourceNotFoundException;
import com.springboot.rest.model.Book;
import com.springboot.rest.repository.BookRepository;
import com.springboot.rest.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;
	
	
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}


	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}


	@Override
	public List<Book> getAllBoooks() {
		return bookRepository.findAll();
	}


	@Override
	public Book getBookById(long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return book.get();
		} else {
			throw new ResourceNotFoundException("Book", "id", id);
		}
	}


	@Override
	public Book updateBook(Book book, long id) {
		Optional<Book> existingBook = bookRepository.findById(id);
		if (!existingBook.isPresent()) {
			throw new ResourceNotFoundException("Book", "id", id);
		}
		
		Book updateBook = existingBook.get();
		
		updateBook.setName(book.getName());
		updateBook.setAuthor(book.getAuthor());
		updateBook.setPrice(book.getPrice());
		
		bookRepository.save(updateBook);
		
		return updateBook;
	}


	@Override
	public void deleteBook(long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			bookRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Book", "id", id);
		}
		
	}

}

package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.service.IBookService;
import com.lms.util.ResponseStructure;

@RestController
@RequestMapping("/book")
public class BookController {

	
	@Autowired
	IBookService bookService;
	
	//{
	//"title":"harry potter",
	//"author":"jk rowling",
	//}
	
	@PostMapping("/{libraryId}")
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody BookDto bookDto,@PathVariable int libraryId){
		return bookService.saveBook(bookDto,libraryId);
	}
	
	@PutMapping("/{bookId}/{userId}")
	public ResponseEntity<ResponseStructure<Book>> borrowBook(
	        @PathVariable int bookId,
	        @PathVariable int userId) {
	    return bookService.borrowBook(bookId, userId);
	}

	@GetMapping
    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
        return bookService.getAllBooks();
    }
	
	@GetMapping("/{bookId}")
    public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable int bookId) {
        return bookService.getBookById(bookId);
    }
	
	
	@PutMapping("/{bookId}")
    public ResponseEntity<ResponseStructure<Book>> updateBook(@PathVariable int bookId, @RequestBody BookDto bookDto) {
        return bookService.updateBook(bookId, bookDto);
    }
}

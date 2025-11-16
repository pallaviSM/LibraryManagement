package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.util.ResponseStructure;

public interface IBookService {

	ResponseEntity<ResponseStructure<Book>> saveBook(BookDto bookDto, int libraryId);

	ResponseEntity<ResponseStructure<Book>> borrowBook(int bookId, int userId);

	ResponseEntity<ResponseStructure<List<Book>>> getAllBooks();

	ResponseEntity<ResponseStructure<Book>> getBookById(int bookId);

	ResponseEntity<ResponseStructure<Book>> updateBook(int bookId, BookDto bookDto);

}

package com.lms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.BookDto;
import com.lms.entity.Book;
import com.lms.entity.Library;
import com.lms.entity.User;
import com.lms.exception.BookUnableToBorrowException;
import com.lms.exception.LibraryIdNotFoundException;
import com.lms.repository.BookRepository;
import com.lms.repository.LibraryRepository;
import com.lms.repository.UserRepository;
import com.lms.util.ResponseStructure;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired 
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;
    
    
    public ResponseEntity<ResponseStructure<Book>> saveBook(BookDto bookDto, int libraryId) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
        
        
        Optional<Library> optional = libraryRepository.findById(libraryId);
        if (optional.isPresent()) {
            Library library = optional.get();
            List<Book> listBooks = library.getBooks();
            if (listBooks == null) {
                listBooks = new ArrayList<>();
            }
            listBooks.add(book);

            library.setBooks(listBooks);
            libraryRepository.save(library);

            ResponseStructure<Book> responseStructure = new ResponseStructure<>();
            responseStructure.setData(book);
            responseStructure.setMessage("Book saved and added to library");
            responseStructure.setStatusCode(HttpStatus.CREATED.value());

            return new ResponseEntity<ResponseStructure<Book>>(responseStructure, HttpStatus.CREATED);
        } else {
            throw new LibraryIdNotFoundException("Library for this ID not found");
        }
    }
    
    public ResponseEntity<ResponseStructure<Book>> borrowBook(int bookId,int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (userOptional.isPresent() && bookOptional.isPresent() && !bookOptional.get().isBorrowed()) {
            User user = userOptional.get();
            Book book = bookOptional.get();
            book.setUser(user);
            book.setBorrowedTime(LocalDateTime.now());
            book.setBorrowed(true);
            bookRepository.save(book);    

            ResponseStructure<Book> responseStructure = new ResponseStructure<>();
            responseStructure.setData(book);
            responseStructure.setMessage("Book Borrowed Successfully By the user");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            throw new BookUnableToBorrowException("BookId or userId not found or Book is already borrowed");
        }
    }

    public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        ResponseStructure<List<Book>> response = new ResponseStructure<>();
        response.setData(books);
        response.setMessage("All books fetched successfully");
        response.setStatusCode(HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
    
    public ResponseEntity<ResponseStructure<Book>> getBookById(int bookId) {
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isPresent()) {
            ResponseStructure<Book> response = new ResponseStructure<>();
            response.setData(optional.get());
            response.setMessage("Book fetched successfully");
            response.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new BookUnableToBorrowException("Book not found with ID: " + bookId);
        }
    }
    
    
    
    public ResponseEntity<ResponseStructure<Book>> updateBook(int bookId, BookDto bookDto) {
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isPresent()) {
            Book existingBook = optional.get();
            existingBook.setTitle(bookDto.getTitle());
            existingBook.setAuthor(bookDto.getAuthor());
            existingBook.setBorrowed(bookDto.isBorrowed());
            existingBook.setBorrowedTime(bookDto.getBorrowedTime());
            existingBook.setReturnTime(bookDto.getReturnTime());

            bookRepository.save(existingBook);

            ResponseStructure<Book> response = new ResponseStructure<>();
            response.setData(existingBook);
            response.setMessage("Book updated successfully");
            response.setStatusCode(HttpStatus.OK.value());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new BookUnableToBorrowException("Book not found for update with ID: " + bookId);
        }
    }
}

package com.lms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.LibraryManagementSystemApplication;
import com.lms.dto.LibraryDto;
import com.lms.entity.Library;
import com.lms.exception.LibraryIdNotFoundException;
import com.lms.repository.LibraryRepository;
import com.lms.util.ResponseStructure;

@Service
public class LibraryService implements ILibraryService{
	
	private final LibraryManagementSystemApplication libraryManagementSystemApplication;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LibraryService(LibraryManagementSystemApplication libraryManagementSystemApplication) {
        this.libraryManagementSystemApplication = libraryManagementSystemApplication;
    }

    // Save Library
    public ResponseEntity<ResponseStructure<Library>> saveLibrary(LibraryDto libraryDto) {
        Library library = modelMapper.map(libraryDto, Library.class);
        libraryRepository.save(library);

        ResponseStructure<Library> response = new ResponseStructure<>();
        response.setMessage("Library saved successfully.");
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(library);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update Library
    public ResponseEntity<ResponseStructure<Library>> updateLibrary(int libraryId, LibraryDto libraryDto) {
        Optional<Library> optional = libraryRepository.findById(libraryId);
        ResponseStructure<Library> response = new ResponseStructure<>();

        if (optional.isPresent()) {
            Library existingLibrary = optional.get();
            existingLibrary.setLibraryName(libraryDto.getLibraryName());
            existingLibrary.setPhoneNumber(libraryDto.getPhoneNumber());

            libraryRepository.save(existingLibrary);

            response.setMessage("Library updated successfully.");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(existingLibrary);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new LibraryIdNotFoundException("Library ID " + libraryId + " not found.");
        }
    }

    // Delete Library
    public ResponseEntity<ResponseStructure<String>> deleteLibrary(int libraryId) {
        Optional<Library> optional = libraryRepository.findById(libraryId);
        ResponseStructure<String> response = new ResponseStructure<>();

        if (optional.isPresent()) {
            libraryRepository.delete(optional.get());
            response.setMessage("Library deleted successfully.");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData("Library ID " + libraryId + " removed.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new LibraryIdNotFoundException("Library ID " + libraryId + " not found.");
        }
    }

    // Fetch All Libraries
    public ResponseEntity<ResponseStructure<List<Library>>> fetchAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        ResponseStructure<List<Library>> response = new ResponseStructure<>();

        if (!libraries.isEmpty()) {
            response.setMessage("All libraries fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(libraries);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("No libraries found in the database.");
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Fetch Library By ID
    public ResponseEntity<ResponseStructure<Library>> fetchLibraryById(int libraryId) {
        Optional<Library> optional = libraryRepository.findById(libraryId);
        ResponseStructure<Library> response = new ResponseStructure<>();

        if (optional.isPresent()) {
            response.setMessage("Library fetched successfully.");
            response.setStatusCode(HttpStatus.OK.value());
            response.setData(optional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new LibraryIdNotFoundException("Library ID " + libraryId + " not found.");
        }

    }
}

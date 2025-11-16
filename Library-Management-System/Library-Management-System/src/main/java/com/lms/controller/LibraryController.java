package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.LibraryDto;
import com.lms.entity.Library;
import com.lms.service.ILibraryService;
import com.lms.service.IUserService;
import com.lms.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	ILibraryService libraryService;
	
	
	@PostMapping
    public ResponseEntity<ResponseStructure<Library>> saveLibrary(@Valid @RequestBody LibraryDto libraryDto) {
        return libraryService.saveLibrary(libraryDto);
    }

    // Update Library
    @PutMapping("/{libraryId}")
    public ResponseEntity<ResponseStructure<Library>> updateLibrary(@PathVariable int libraryId,
                                                                    @Valid @RequestBody LibraryDto libraryDto) {
        return libraryService.updateLibrary(libraryId, libraryDto);
    }

    // Delete Library
    @DeleteMapping("/{libraryId}")
    public ResponseEntity<ResponseStructure<String>> deleteLibrary(@PathVariable int libraryId) {
        return libraryService.deleteLibrary(libraryId);
    }

    // Fetch All Libraries
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Library>>> fetchAllLibraries() {
        return libraryService.fetchAllLibraries();
    }

    // Fetch Library by ID
    @GetMapping("/{libraryId}")
    public ResponseEntity<ResponseStructure<Library>> fetchLibraryById(@PathVariable int libraryId) {
        return libraryService.fetchLibraryById(libraryId);
    }
}

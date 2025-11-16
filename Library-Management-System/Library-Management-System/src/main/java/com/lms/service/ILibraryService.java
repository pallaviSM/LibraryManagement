package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.LibraryDto;
import com.lms.entity.Library;
import com.lms.util.ResponseStructure;

import jakarta.validation.Valid;

public interface ILibraryService {

	ResponseEntity<ResponseStructure<Library>> saveLibrary(@Valid LibraryDto libraryDto);

	ResponseEntity<ResponseStructure<Library>> updateLibrary(int libraryId, @Valid LibraryDto libraryDto);

	ResponseEntity<ResponseStructure<String>> deleteLibrary(int libraryId);

	ResponseEntity<ResponseStructure<List<Library>>> fetchAllLibraries();

	ResponseEntity<ResponseStructure<Library>> fetchLibraryById(int libraryId);

}

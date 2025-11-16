package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

}

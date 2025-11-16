package com.lms.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Library {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="libraryid_generator")
	@SequenceGenerator(name="libraryid_generator",initialValue=101,allocationSize=1)
	private int libraryId;
	private String libraryName;
	@Column(unique=true)
	private long PhoneNumber;
	@OneToOne
	private Address address;
	
	@OneToMany
	private List<Book> books;
}

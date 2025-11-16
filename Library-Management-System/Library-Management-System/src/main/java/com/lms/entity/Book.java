package com.lms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="bookid_generator")
	@SequenceGenerator(name="bookid_generator",initialValue=101,allocationSize=1)
	private int bookId;
	private String title;
	private String author;
	private boolean borrowed;
	private LocalDateTime borrowedTime;
	private LocalDateTime returnTime;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	
}

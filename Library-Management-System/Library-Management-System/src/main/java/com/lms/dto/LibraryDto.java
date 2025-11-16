package com.lms.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryDto {

	private int libraryId;
	@NotBlank
	@NotNull
	private String libraryName;
	@Digits(integer=10,fraction=0)
	@Min(value=700000000)
	@Max(value=999999999)
	private long PhoneNumber;
}

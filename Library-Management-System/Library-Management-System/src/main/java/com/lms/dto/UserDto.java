package com.lms.dto;




import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private int userId;
	private String userName;
	@Digits(integer=10,fraction=0)
	@Min(value=700000000)
	@Max(value=999999999)
	private long phoneNumber;
	@Email
	private String email;
	
}

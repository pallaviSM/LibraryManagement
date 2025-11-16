package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.util.ResponseStructure;

import jakarta.validation.Valid;

public interface IUserService {

	ResponseEntity<ResponseStructure<User>> saveUser(@Valid UserDto userDto, int addressId);

	ResponseEntity<ResponseStructure<User>> updateUser(int userId, @Valid UserDto userDto);

	ResponseEntity<ResponseStructure<String>> deleteUser(int userId);

	ResponseEntity<ResponseStructure<List<User>>> findAllUser();

	ResponseEntity<ResponseStructure<List<User>>> FetchAllUsers();

}

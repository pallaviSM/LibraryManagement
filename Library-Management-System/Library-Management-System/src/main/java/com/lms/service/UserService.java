package com.lms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.LibraryManagementSystemApplication;
import com.lms.dto.UserDto;
import com.lms.entity.Address;
import com.lms.entity.User;
import com.lms.exception.AddressIdNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.UserRepository;
import com.lms.util.ResponseStructure;

@Service
public class UserService implements IUserService{
	
	private final LibraryManagementSystemApplication libraryManagementSystemApplication;
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	
	UserService(LibraryManagementSystemApplication libraryManagementSystemApplication){
		this.libraryManagementSystemApplication=libraryManagementSystemApplication;
	}
	
	public ResponseEntity<ResponseStructure<User>> saveUser(UserDto userDto,int addressId){
		Optional<Address> optional=addressRepository.findById(addressId);
		User user=modelMapper.map(userDto, User.class);
		if(optional.isPresent()) {
			user.setAddress(optional.get());
			userRepository.save(user);
			ResponseStructure<User> responseStructure=new ResponseStructure<>();
			responseStructure.setMessage("User With Address Saved Succesfully");
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setData(user);
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED);
		}
		else
		{
			throw new AddressIdNotFoundException("Address id not found..");
		}
	}
	
	@Override
    public ResponseEntity<ResponseStructure<User>> updateUser(int userId, UserDto userDto) {
        Optional<User> optional = userRepository.findById(userId);
        ResponseStructure<User> structure = new ResponseStructure<>();

        if (optional.isPresent()) {
            User existingUser = optional.get();
            existingUser.setUserName(userDto.getUserName());
            existingUser.setPhoneNumber(userDto.getPhoneNumber());
            existingUser.setEmail(userDto.getEmail());
            userRepository.save(existingUser);

            structure.setMessage("User updated successfully.");
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setData(existingUser);

            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            structure.setMessage("User ID not found. Update failed.");
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }
	@Override
    public ResponseEntity<ResponseStructure<String>> deleteUser(int userId) {
        Optional<User> optional = userRepository.findById(userId);
        ResponseStructure<String> structure = new ResponseStructure<>();

        if (optional.isPresent()) {
            userRepository.delete(optional.get());
            structure.setMessage("User deleted successfully.");
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setData("User ID " + userId + " removed.");

            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            structure.setMessage("User ID not found. Deletion failed.");
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }
	
	@Override
    public ResponseEntity<ResponseStructure<List<User>>> findAllUser() {
        List<User> users = userRepository.findAll();
        ResponseStructure<List<User>> structure = new ResponseStructure<>();

        if (!users.isEmpty()) {
            structure.setMessage("All users fetched successfully.");
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setData(users);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            structure.setMessage("No users found in the database.");
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }
	
	
	@Override
    public ResponseEntity<ResponseStructure<List<User>>> FetchAllUsers() {
        List<User> users = userRepository.findAll();
        ResponseStructure<List<User>> structure = new ResponseStructure<>();

        if (!users.isEmpty()) {
            structure.setMessage("All users fetched successfully.");
            structure.setStatusCode(HttpStatus.OK.value());
            structure.setData(users);
            return new ResponseEntity<>(structure, HttpStatus.OK);
        } else {
            structure.setMessage("No users found in the database.");
            structure.setStatusCode(HttpStatus.NOT_FOUND.value());
            structure.setData(null);
            return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }
}


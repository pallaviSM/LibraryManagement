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

import com.lms.dto.UserDto;
import com.lms.entity.User;
import com.lms.service.IUserService;
import com.lms.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //  Create User (POST)
    @PostMapping("/{addressId}")
    public ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestBody UserDto userDto,@PathVariable int addressId) {
        return userService.saveUser(userDto, addressId);
    }

    // Update User (PUT)
    @PutMapping("/{userId}")
    public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int userId,
                                                              @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    //  Delete User (DELETE)
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    //  Fetch All Users 
    @GetMapping("/all")
    public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
        return userService.findAllUser();
    }

    // Fetch All Users 
    @GetMapping
    public ResponseEntity<ResponseStructure<List<User>>> fetchAllUsers() {
        return userService.FetchAllUsers();
    }
}

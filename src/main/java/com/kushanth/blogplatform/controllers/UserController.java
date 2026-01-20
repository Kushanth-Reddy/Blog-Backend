package com.kushanth.blogplatform.controllers;

import com.kushanth.blogplatform.entities.User;
import com.kushanth.blogplatform.entities.dto.UserDto;
import com.kushanth.blogplatform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public UserDto registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

}

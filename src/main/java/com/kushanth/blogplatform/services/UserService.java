package com.kushanth.blogplatform.services;

import com.kushanth.blogplatform.entities.BlogPost;
import com.kushanth.blogplatform.entities.Comment;
import com.kushanth.blogplatform.entities.User;
import com.kushanth.blogplatform.entities.dto.UserDto;
import com.kushanth.blogplatform.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto userToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPosts(user.getPosts().stream()
                .map(BlogPost::getTitle)
                .collect(Collectors.toList())
        );
        userDto.setComments(user.getComments().stream()
                .map(Comment::getText)
                .collect(Collectors.toList())
        );

        return userDto;
    }

    @Transactional
    public UserDto createUser(User user) {

        user.setPosts(new ArrayList<>());
        user.setComments(new ArrayList<>());

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");

        User savedUser = userRepo.save(user);

        return userToDto(savedUser);
    }


    @Transactional
    public UserDto getUserById(Long id) {
        User user = userRepo.findById(id).orElse(new User());

        if (user.getUsername() == null) return new UserDto();

        return userToDto(user);
    }
}

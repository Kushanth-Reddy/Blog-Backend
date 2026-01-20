package com.kushanth.blogplatform.entities.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private List<String> posts;
    private List<String> comments;
}

package com.kushanth.blogplatform.entities.dto;

import lombok.Data;

@Data
public class BlogPostDto {
    private String title;
    private String content;
    private Long userId;
}

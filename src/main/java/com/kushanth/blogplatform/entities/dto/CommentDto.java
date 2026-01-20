package com.kushanth.blogplatform.entities.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long userId;
    private String text;
}

package com.kushanth.blogplatform.entities.dto.ResponseDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BlogPostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private List<String> comments;
}

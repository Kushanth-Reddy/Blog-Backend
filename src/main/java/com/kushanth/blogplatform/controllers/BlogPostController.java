package com.kushanth.blogplatform.controllers;

import com.kushanth.blogplatform.entities.dto.BlogPostDto;
import com.kushanth.blogplatform.entities.dto.ResponseDto.BlogPostResponseDto;
import com.kushanth.blogplatform.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService postService;

    @PostMapping("")
    public BlogPostResponseDto createPost(@RequestBody BlogPostDto post) {
        return postService.createBlogPost(post);
    }

    @GetMapping("")
    public List<BlogPostResponseDto> getPosts() {
        return postService.getAllPosts();
    }
    @GetMapping("/{postId}")
    public BlogPostResponseDto getPost(@PathVariable("postId") Long postId) {
        return postService.getPost(postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Long postId) {
        postService.deletePostById(postId);
    }

}

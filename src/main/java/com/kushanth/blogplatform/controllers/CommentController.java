package com.kushanth.blogplatform.controllers;

import com.kushanth.blogplatform.entities.dto.CommentDto;
import com.kushanth.blogplatform.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService ;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public CommentDto addComment(@PathVariable("postId") Long postId, @RequestBody CommentDto commentDto) {
        return commentService.createComment(postId, commentDto);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }
}

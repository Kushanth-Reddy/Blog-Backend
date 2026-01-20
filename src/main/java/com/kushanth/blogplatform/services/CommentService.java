package com.kushanth.blogplatform.services;

import com.kushanth.blogplatform.entities.BlogPost;
import com.kushanth.blogplatform.entities.Comment;
import com.kushanth.blogplatform.entities.User;
import com.kushanth.blogplatform.entities.dto.CommentDto;
import com.kushanth.blogplatform.repositories.BlogPostRepo;
import com.kushanth.blogplatform.repositories.CommentRepo;
import com.kushanth.blogplatform.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final BlogPostRepo blogPostRepo;
    private final UserRepo userRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo, BlogPostRepo blogPostRepo, UserRepo userRepo) {
        this.commentRepo = commentRepo;
        this.blogPostRepo = blogPostRepo;
        this.userRepo = userRepo;
    }

    public Comment dtoToComment(CommentDto commentDto, User user, BlogPost post) {

        Comment comment = new Comment();

        comment.setText(commentDto.getText());
        comment.setCreatedDate(LocalDateTime.now());
        comment.setUser(user);
        comment.setPost(post);

        return comment;
    }
    @Transactional
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        BlogPost post =  blogPostRepo.findById(postId).orElse(new BlogPost());
        if (post.getTitle() == null) return new CommentDto();

        User user = userRepo.findById(commentDto.getUserId()).orElse(new User());
        if (user.getUsername() == null) return new CommentDto();

        Comment comment = dtoToComment(commentDto, user, post);

        commentRepo.save(comment);

        return commentDto;
    }
    @Transactional
    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
}

package com.kushanth.blogplatform.services;

import com.kushanth.blogplatform.entities.BlogPost;
import com.kushanth.blogplatform.entities.Comment;
import com.kushanth.blogplatform.entities.User;
import com.kushanth.blogplatform.entities.dto.BlogPostDto;
import com.kushanth.blogplatform.entities.dto.ResponseDto.BlogPostResponseDto;
import com.kushanth.blogplatform.repositories.BlogPostRepo;
import com.kushanth.blogplatform.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    private final BlogPostRepo blogpostRepo;
    private final UserRepo userRepo;

    @Autowired
    public BlogPostService(UserRepo userRepo, BlogPostRepo blogpostRepo) {
        this.userRepo = userRepo;
        this.blogpostRepo = blogpostRepo;
    }

    public static BlogPostResponseDto postToDto(BlogPost post) {

        BlogPostResponseDto blogPostResponseDto = new BlogPostResponseDto();

        blogPostResponseDto.setId(post.getId());
        blogPostResponseDto.setTitle(post.getTitle());
        blogPostResponseDto.setContent(post.getContent());
        blogPostResponseDto.setUserId(post.getUser().getId());
        blogPostResponseDto.setComments(post.getComments().stream()
                .map(Comment::getText)
                .collect(Collectors.toList())
        );

        for (Comment comment : post.getComments())
            System.out.print(comment.getText() + "                  ");

        return blogPostResponseDto;
    }

    @Transactional
    public BlogPostResponseDto createBlogPost(BlogPostDto post) {
        User user = userRepo.findById(post.getUserId()).orElse(new User());
        if (user.getUsername() == null) {
            return new BlogPostResponseDto();
        }

        BlogPost newPost = new BlogPost();

        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setCreatedDate(LocalDateTime.now());
        newPost.setUser(user);
        newPost.setComments(new ArrayList<>());

        BlogPost savedPost =  blogpostRepo.save(newPost);
        return postToDto(savedPost);
    }

    @Transactional
    public List<BlogPostResponseDto> getAllPosts() {
        return blogpostRepo.findAll().stream()
                .map(BlogPostService::postToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BlogPostResponseDto getPost(Long id) {
        BlogPost post = blogpostRepo.findById(id).orElse(new BlogPost());
        return postToDto(post);
    }

    @Transactional
    public void deletePostById(Long postId) {
        blogpostRepo.deleteById(postId);
    }
}

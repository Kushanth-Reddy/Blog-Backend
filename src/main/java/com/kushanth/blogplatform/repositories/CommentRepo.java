package com.kushanth.blogplatform.repositories;

import com.kushanth.blogplatform.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
}

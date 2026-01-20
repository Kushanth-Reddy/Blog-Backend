package com.kushanth.blogplatform.repositories;

import com.kushanth.blogplatform.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost, Long> {
}

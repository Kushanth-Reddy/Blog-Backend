package com.kushanth.blogplatform.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blog_post_id", referencedColumnName = "id", nullable = false)
    private BlogPost post;

}

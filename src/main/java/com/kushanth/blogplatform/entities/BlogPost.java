package com.kushanth.blogplatform.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class BlogPost {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT", name = "content", nullable = false)
    private String content;

    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

}

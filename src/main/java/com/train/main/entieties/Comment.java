package com.train.main.entieties;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Getter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "issue_id")
    @ManyToOne
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @Column(name = "text")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    public Comment() {
    }

    public Comment(Issue issue, User user, String description) {
        this.issue = issue;
        this.user = user;
        this.description = description;
        this.createdAt = Instant.now();
    }
}

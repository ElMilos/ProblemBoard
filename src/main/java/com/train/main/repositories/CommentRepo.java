package com.train.main.repositories;

import com.train.main.entieties.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findAllByIssue(Long issueId);
}

package com.train.main.service;

import com.train.main.DTO.IssueRequest;
import com.train.main.entieties.Comment;
import com.train.main.entieties.Issue;
import com.train.main.entieties.User;
import com.train.main.repositories.CommentRepo;
import com.train.main.repositories.IssueRepo;
import com.train.main.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final IssueRepo issueRepo;
    private final UserRepo userRepo;

    public List<Comment> listByIssue(Long issueId) {

        return commentRepo.findAllByIssue(issueId);
    }

    @Transactional
    public Comment create(Long issueId, IssueRequest req) {
        Issue issue = issueRepo.findById(issueId).orElseThrow(EntityNotFoundException::new);

        String username = currentUsername();
        User author = userRepo.findByUsername(username).orElseThrow(EntityNotFoundException::new);

        Comment c = new Comment();
        c.setIssue(issue);
        c.setUser(author);
        c.setDescription(req.getDescription());

        return commentRepo.save(c);
    }

    private String currentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null || auth.getName().isBlank()) {
            throw new EntityNotFoundException("Authenticated user not found");
        }
        return auth.getName();
    }
}

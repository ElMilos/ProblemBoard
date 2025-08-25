package com.train.main.service;

import com.train.main.DTO.IssueRequest;
import com.train.main.crud.IssueFilter;
import com.train.main.entieties.Issue;
import com.train.main.entieties.IssuePriority;
import com.train.main.entieties.IssueStatus;
import com.train.main.entieties.Project;
import com.train.main.repositories.IssueRepo;
import com.train.main.repositories.ProjectRepo;
import com.train.main.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepo issueRepo;
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    public Issue create(Long projectId, IssueRequest issueRequest) {
        Project project = projectRepo.findById(projectId).orElseThrow(EntityNotFoundException::new);
        Issue issue = new Issue(project, issueRequest.getTitle(), issueRequest.getDescription(),
        IssueStatus.OPEN, issueRequest.getPriority(),
        userRepo.findById(issueRequest.getAssigneeId()).orElseThrow(EntityNotFoundException::new));
        return issueRepo.save(issue);
    }

    public List<Issue> getList(Long projectId, Optional<IssueStatus> st, Optional<IssuePriority> pr, Optional<Long> assignee, Pageable pageable)
    {
        Specification<Issue> spec = Specification.allOf(IssueFilter.byProject(projectId));
        if(st.isPresent()) spec = spec.and(IssueFilter.status(st.get()));
        if(assignee.isPresent()) spec = spec.and(IssueFilter.assignee(assignee.get()));
        return issueRepo.findAllLimited(spec, pageable);
    }

}

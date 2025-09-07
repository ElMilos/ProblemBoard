package com.train.main.service;

import com.train.main.DTO.IssueRequest;
import com.train.main.entieties.Project;
import com.train.main.repositories.ProjectRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;

    public Project create(IssueRequest req) {
        Project p = new Project();
        p.setName(req.getTitle());
        p.setDescription(req.getDescription());
        p.setArchived(false);
        return projectRepo.save(p);
    }

    public Project get(Long id) {
        return projectRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Project> getList(Pageable pageable) {
        return projectRepo.findAll(pageable).getContent();
    }

    public Project update(Long id, IssueRequest req) {
        Project p = projectRepo.findById(id).orElseThrow(EntityNotFoundException::new);

        if (req.getTitle() != null) {
            p.setName(req.getTitle());
        }
        if (req.getDescription() != null) {
            p.setDescription(req.getDescription());
        }

        return projectRepo.save(p);
    }

    public void delete(Long id) {
        Project p = projectRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        projectRepo.delete(p);
    }
}
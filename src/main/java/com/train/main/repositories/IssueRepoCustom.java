package com.train.main.repositories;

import com.train.main.entieties.Issue;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IssueRepoCustom {
    List<Issue> findAllLimited(Specification<Issue> spec, Pageable pageable);

}

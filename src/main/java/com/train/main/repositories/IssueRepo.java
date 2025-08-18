package com.train.main.repositories;

import com.train.main.entieties.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepo extends JpaRepository<Issue,Long> {
}

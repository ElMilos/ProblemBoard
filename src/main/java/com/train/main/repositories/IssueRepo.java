package com.train.main.repositories;

import com.train.main.entieties.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IssueRepo extends JpaRepository<Issue,Long>, JpaSpecificationExecutor<Issue> {

}

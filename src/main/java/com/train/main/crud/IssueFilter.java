package com.train.main.crud;

import com.train.main.entieties.Issue;
import com.train.main.entieties.IssueStatus;
import org.springframework.data.jpa.domain.Specification;

public final class IssueFilter {
        public static Specification<Issue> byProject(Long pid){ return (r, q, cb)-> cb.equal(r.get("project").get("id"), pid); }
        public static Specification<Issue> status(IssueStatus s){ return (r, q, cb)-> cb.equal(r.get("status"), s); }
        public static Specification<Issue> assignee(Long uid){ return (r, q, cb)-> cb.equal(r.get("assignee").get("id"), uid); }
    }



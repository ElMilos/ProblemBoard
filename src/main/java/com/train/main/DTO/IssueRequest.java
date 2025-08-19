package com.train.main.DTO;

import com.train.main.entieties.IssuePriority;
import com.train.main.entieties.IssueStatus;
import lombok.Getter;

@Getter
public class IssueRequest {
    String title;
    String description;
    IssuePriority priority;
    Long assigneeId;
}


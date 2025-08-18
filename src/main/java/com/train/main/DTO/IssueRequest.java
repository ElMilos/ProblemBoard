package com.train.main.DTO;

import com.train.main.entieties.IssuePriority;

public class IssueRequest {
    String title;
    String description;
    IssuePriority priority;
    Long assigneeId;
}


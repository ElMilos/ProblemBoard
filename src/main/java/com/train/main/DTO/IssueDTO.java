package com.train.main.DTO;
import com.train.main.entieties.IssuePriority;
import com.train.main.entieties.IssueStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@RequiredArgsConstructor
public class IssueDTO {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;
    private Long assigneeId;
    private Instant createdAt;
    private Instant updatedAt;
}

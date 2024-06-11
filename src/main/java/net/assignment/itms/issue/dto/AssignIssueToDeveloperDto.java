package net.assignment.itms.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssignIssueToDeveloperDto {
    private Long assignee_id;
    private Long issue_id;
}

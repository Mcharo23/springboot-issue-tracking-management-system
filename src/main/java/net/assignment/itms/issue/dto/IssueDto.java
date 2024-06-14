package net.assignment.itms.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.assignment.itms.issue.entity.IssueType;
import net.assignment.itms.project.entity.Project;
import net.assignment.itms.user.entity.User;
import net.assignment.itms.utils.Priority;
import net.assignment.itms.utils.Status;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDto {
    private Long issue_id;

    private Long project_id;
    private Project project;

    private Long issue_type_id;
    private IssueType issueType;

    private String Summary;

    private String description;

    private Priority priority;

    private Status status;

    private User reporter;

    private Long assignee_id;
    private User assignee;

}

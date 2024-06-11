package net.assignment.itms.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.assignment.itms.utils.Priority;
import net.assignment.itms.utils.Status;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueDetailDto {
    private Long issue_id;
    private Long project_id;
    private String project_name;
    private Long issue_type_id;
    private String issue_type;
    private String Summary;
    private String description;
    private Priority priority;
    private Status status;
    private Long reporter_id;
    private Long assignee_id;
    private String developer;
    private Set<CommentDetailDto> comments;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

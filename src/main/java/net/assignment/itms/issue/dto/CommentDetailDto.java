package net.assignment.itms.issue.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDetailDto {
    private Long comment_id;

    private Long issue_id;

    private Long user_id;

    private String comment;

    private LocalDateTime created_at;
}

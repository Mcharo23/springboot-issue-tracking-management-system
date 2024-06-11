package net.assignment.itms.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.assignment.itms.issue.entity.Issue;
import net.assignment.itms.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long comment_id;

    private Issue issue;

    private User user;

    private String comment;

    private LocalDateTime created_at;
}



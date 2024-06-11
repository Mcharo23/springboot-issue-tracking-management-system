package net.assignment.itms.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.assignment.itms.utils.Status;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStatusDto {
    private Long issue_id;
    private Long user_id;
    private Status status;
    private String comment;
}

package net.assignment.itms.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IssueTypeDto {
    private Long issue_type_id;
    private String name;
}

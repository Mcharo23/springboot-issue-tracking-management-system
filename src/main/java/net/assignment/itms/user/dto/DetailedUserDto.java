package net.assignment.itms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.assignment.itms.utils.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailedUserDto {
    private String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private Role role;
    private Boolean is_active;
    private Boolean is_deleted;
    private List<String> reported_issues;
    private List<String> assigned_issues;
}

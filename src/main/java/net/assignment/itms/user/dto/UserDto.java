package net.assignment.itms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.assignment.itms.utils.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Role role;
}

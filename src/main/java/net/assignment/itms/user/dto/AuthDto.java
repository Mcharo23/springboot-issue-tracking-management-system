package net.assignment.itms.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "AuthDto{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

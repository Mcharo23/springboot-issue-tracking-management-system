package net.assignment.itms.user.mapper;

import net.assignment.itms.user.dto.DetailedUserDto;
import net.assignment.itms.user.dto.UserDto;
import net.assignment.itms.user.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getUser_id());
        userDto.setFirst_name(user.getFirst_name());
        userDto.setLast_name(user.getLast_name());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUser_id(userDto.getUser_id());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public static DetailedUserDto mapToDetailedUserDto(User user) {
        DetailedUserDto detailedUserDto = new DetailedUserDto();
        detailedUserDto.setUser_id(String.valueOf(user.getUser_id()));
        detailedUserDto.setFirst_name(user.getFirst_name());
        detailedUserDto.setLast_name(user.getLast_name());
        detailedUserDto.setEmail(user.getEmail());
        detailedUserDto.setRole(user.getRole());
        detailedUserDto.setIs_active(user.getIs_active());
        detailedUserDto.setIs_deleted(user.getIs_deleted());
        detailedUserDto.setReported_issues(
                user.getReported_issues().stream()
                        .map(issue -> String.valueOf(issue.getIssue_id()))
                        .collect(Collectors.toList())
        );
        detailedUserDto.setAssigned_issues(
                user.getAssigned_issues().stream()
                        .map(issue -> String.valueOf(issue.getIssue_id()))
                        .collect(Collectors.toList())
        );
        return detailedUserDto;
    }
}

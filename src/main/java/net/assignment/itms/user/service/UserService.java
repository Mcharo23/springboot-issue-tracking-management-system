package net.assignment.itms.user.service;


import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.UpdateUserPassword;
import net.assignment.itms.user.dto.AuthDto;
import net.assignment.itms.user.dto.DetailedUserDto;
import net.assignment.itms.user.dto.UserDto;
import net.assignment.itms.user.controller.AuthenticationResponse;
import net.assignment.itms.user.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<DetailedUserDto> getAllUsers();
    User findUser(Long user_id) throws NotFoundException;
    String createUser(UserDto userDto) throws BadRequestException;
    AuthenticationResponse authentication(AuthDto authDto);
    String activateUser(Long user_id) throws NotFoundException;
    String deleteUser(Long user_id) throws NotFoundException;

    User findUserByEmail(String email);
    Map<String, String> updateUserPassword(String email, UpdateUserPassword updateUserPassword) throws Exception;
}

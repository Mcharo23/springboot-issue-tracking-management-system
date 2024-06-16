package net.assignment.itms.user.controller;

import net.assignment.itms.config.JwtService;
import net.assignment.itms.config.SecurityUtils;
import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.UpdateUserPassword;
import net.assignment.itms.user.dto.DetailedUserDto;
import net.assignment.itms.user.dto.UserDto;
import net.assignment.itms.user.dto.UserIdDto;
import net.assignment.itms.user.entity.User;
import net.assignment.itms.user.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(PasswordEncoder passwordEncoder, UserServiceImpl userService, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<DetailedUserDto>> getAllUsers() {
        List<DetailedUserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserDto userDto) {
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getLast_name().toUpperCase()));

            String message = userService.createUser(userDto);

            Map<String, String> map = new HashMap<>();
            map.put("detail", message);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("detail", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> activateUser(@RequestBody UserIdDto userIdDto) {

        Map<String, String> map = new HashMap<>();
        try {
            String result = userService.activateUser(userIdDto.getUser_id());

            map.put("detail", result);

            return ResponseEntity.ok(map);
        } catch (NotFoundException e) {
            map.put("detail", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteUser(@RequestBody UserIdDto userIdDto) {
        //logger.info("Received authentication request: {}", userIdDto.getUser_id());
        Map<String, String> map = new HashMap<>();

        try {
            String result = userService.deleteUser(userIdDto.getUser_id());
            map.put("detail", result);

            return ResponseEntity.ok(map);
        } catch (NotFoundException e){
            map.put("detail", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping
    public ResponseEntity<Map<String, String>> updateUserPassword(@RequestBody UpdateUserPassword updateUserPassword) {

        try {
            String email = SecurityUtils.getAuthenticatedUserEmail(jwtService);
            Map<String, String> result = userService.updateUserPassword(email, updateUserPassword);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            if (e.getMessage().equals("User not found")) {
                return ResponseEntity.status(404).body(Map.of("detail", "User not found"));
            } else if (e.getMessage().equals("Invalid password")) {
                return ResponseEntity.status(404).body(Map.of("detail", "Wrong old password"));
            } else {
                return ResponseEntity.status(400).body(Map.of("detail", e.getMessage()));
            }
        }

    }
}

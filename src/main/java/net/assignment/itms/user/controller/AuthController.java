package net.assignment.itms.user.controller;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.user.dto.AuthDto;
import net.assignment.itms.user.dto.UserDto;

import net.assignment.itms.user.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;

    public AuthController(PasswordEncoder passwordEncoder, UserServiceImpl userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserDto userDto) throws BadRequestException {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        String message = userService.createUser(userDto);

        Map<String, String> map = new HashMap<>();
        map.put("detail", message);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthDto authDto) {
        // logger.info("Received authentication request: {}", authDto);
        return ResponseEntity.ok(userService.authentication(authDto));
    }

}

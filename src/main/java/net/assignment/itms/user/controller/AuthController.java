package net.assignment.itms.user.controller;

import net.assignment.itms.user.dto.AuthDto;

import net.assignment.itms.user.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthDto authDto) {
        // logger.info("Received authentication request: {}", authDto);
        return ResponseEntity.ok(userService.authentication(authDto));
    }

}

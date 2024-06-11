package net.assignment.itms.user.controller;

import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.user.dto.DetailedUserDto;
import net.assignment.itms.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<DetailedUserDto>> getAllUsers() {
        List<DetailedUserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{user_id}/activate")
    public ResponseEntity<Map<String, String>> activateUser(@PathVariable Long user_id) {
        Map<String, String> map = new HashMap<>();
        try {
            String result = userService.activateUser(user_id);

            map.put("detail", result);

            return ResponseEntity.ok(map);
        } catch (NotFoundException e) {
            map.put("detail", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{user_id}/delete")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long user_id) {
        Map<String, String> map = new HashMap<>();

        try {
            String result = userService.deleteUser(user_id);
            map.put("detail", result);

            return ResponseEntity.ok(map);
        } catch (NotFoundException e){
            map.put("detail", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}

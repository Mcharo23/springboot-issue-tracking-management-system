package net.assignment.itms.user.service.impl;

import net.assignment.itms.config.JwtService;
import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.user.dto.AuthDto;
import net.assignment.itms.user.dto.DetailedUserDto;
import net.assignment.itms.user.dto.UserDto;
import net.assignment.itms.user.mapper.UserMapper;
import net.assignment.itms.user.controller.AuthenticationResponse;
import net.assignment.itms.user.repository.UserRepository;
import net.assignment.itms.user.service.UserService;
import net.assignment.itms.user.entity.User;
import net.assignment.itms.utils.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public List<DetailedUserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> !Role.ADMIN.equals(user.getRole()))
                .map(UserMapper::mapToDetailedUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findUser(Long user_id) throws NotFoundException {

        return userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }


    @Override
    public String createUser(UserDto userDto) throws BadRequestException {
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());

        if (user.isPresent()) {
            throw new BadRequestException("User with email " + userDto.getEmail() + " already exists");
        }

        User savedUser = userRepository.save(UserMapper.mapToUser(userDto));

        if (savedUser == null) {
            throw new RuntimeException("Error creating user. Please try again.");
        }

        return "User created successfully!";
    }

    @Override
    public AuthenticationResponse authentication(AuthDto authDto){
        //logger.info("Received authentication request: {}", authDto);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDto.getEmail(),
                        authDto.getPassword()
                )
        );

        User user = userRepository.findByEmail(authDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + authDto.getEmail()));

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public String activateUser(Long user_id) throws NotFoundException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setIs_active(!user.getIs_active());
        userRepository.save(user);

        return "User " + (user.getIs_active() ? "activated" : "deactivated") + " successfully";
    }

    @Override
    public String deleteUser(Long user_id) throws NotFoundException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setIs_deleted(!user.getIs_deleted());
        userRepository.save(user);
        return "User " + (user.getIs_deleted() ? "deleted " : "activated ") + "successfully";
    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }
}

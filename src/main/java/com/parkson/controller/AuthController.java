package com.parkson.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkson.config.ApiResponse;
import com.parkson.dto.UserDto;
import com.parkson.model.User;
import com.parkson.security.JwtAuthenticationResponse;
import com.parkson.security.JwtTokenProvider;
import com.parkson.service.UserService;


@Profile("!test")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;
	

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto loginRequest) {

    	System.out.println("Authenticate user "+loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        System.out.println("Results "+authentication.getName()+" principal "+authentication.getPrincipal().toString());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));//, authentication.getPrincipal()));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto signUpRequest) {
        if(userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        userService.saveUser(user);


        return ResponseEntity.ok((new ApiResponse(true, "User registered successfully")));
    }
}
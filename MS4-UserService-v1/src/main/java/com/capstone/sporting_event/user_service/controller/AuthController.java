package com.capstone.sporting_event.user_service.controller;

import com.capstone.sporting_event.user_service.dto.UserLoginRequestDTO;
import com.capstone.sporting_event.user_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.capstone.sporting_event.user_service.constants.Mapping.VERSION_1;


@RestController
@RequestMapping(value=VERSION_1)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody UserLoginRequestDTO authRequest) throws RuntimeException{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }
        catch(Exception exception){
            throw new RuntimeException("Invalid Credentials!");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        return jwtUtil.generateToken(userDetails);
    }
}

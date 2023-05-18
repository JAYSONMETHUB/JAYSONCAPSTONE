package com.capstone.sporting_event.user_service.service;


import com.capstone.sporting_event.user_service.exceptions.UserNotFoundException;
import com.capstone.sporting_event.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    public UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.capstone.sporting_event.user_service.entity.User> getUser = repository.findByUserName(username);

        if(getUser.isEmpty())
            throw new UserNotFoundException();

        com.capstone.sporting_event.user_service.entity.User user = getUser.get();

        return new User(user.getUserName(), user.getPassword(),new ArrayList<>());
    }

}

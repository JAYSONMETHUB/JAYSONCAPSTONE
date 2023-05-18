package com.capstone.sporting_event.user_service.service;

import com.capstone.sporting_event.user_service.dto.UserRequestDTO;
import com.capstone.sporting_event.user_service.entity.User;
import com.capstone.sporting_event.user_service.exceptions.UserAlreadyExistException;
import com.capstone.sporting_event.user_service.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User saveUser(UserRequestDTO userRequestDTO) throws UserAlreadyExistException;
    public User saveUserAdmin(UserRequestDTO userRequestDTO) throws UserAlreadyExistException;

    public List<User> getAllUsers();
    public User getUser(int id) throws UserNotFoundException;


    public User updateUser(int id, UserRequestDTO userRequestDTO) throws UserNotFoundException;
    public void deleteUser(int id) throws UserNotFoundException;
}

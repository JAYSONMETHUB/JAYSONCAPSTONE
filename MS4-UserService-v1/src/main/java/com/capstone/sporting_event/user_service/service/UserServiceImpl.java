package com.capstone.sporting_event.user_service.service;


import com.capstone.sporting_event.user_service.dto.UserRequestDTO;
import com.capstone.sporting_event.user_service.dto.UserMapper;
import com.capstone.sporting_event.user_service.entity.User;
import com.capstone.sporting_event.user_service.exceptions.UserAlreadyExistException;
import com.capstone.sporting_event.user_service.exceptions.UserNotFoundException;
import com.capstone.sporting_event.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserMapper userMapper;

    @Override
    public User saveUser(UserRequestDTO userRequestDTO) throws UserAlreadyExistException {

        if(userRepo.findByUserName(userRequestDTO.getUserName()).isPresent()||userRepo.findByEmail(userRequestDTO.getEmail()).isPresent())
            throw new UserAlreadyExistException();


        return userRepo.save(userMapper.toEntityForUser(userRequestDTO));

    }

    @Override
    public User saveUserAdmin(UserRequestDTO userRequestDTO) throws UserAlreadyExistException{

        if(userRepo.findByUserName(userRequestDTO.getUserName()).isPresent()||userRepo.findByEmail(userRequestDTO.getEmail()).isPresent())
            throw new UserAlreadyExistException();


        return userRepo.save(userMapper.toEntityForAdmin(userRequestDTO));

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(int id) throws UserNotFoundException {
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        return user.get();
    }



    @Override
    public User updateUser(int id, UserRequestDTO userRequestDTO){

        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        User updatedUser = user.get();
        updatedUser.setUserName(userRequestDTO.getUserName());
        updatedUser.setEmail(userRequestDTO.getEmail());
        updatedUser.setPassword(userRequestDTO.getPassword());
        updatedUser.setRole(updatedUser.getRole());

        return userRepo.save(updatedUser);

    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        User deletedUser = user.get();
        deletedUser.setActive(false);
        userRepo.save(deletedUser);
    }


}

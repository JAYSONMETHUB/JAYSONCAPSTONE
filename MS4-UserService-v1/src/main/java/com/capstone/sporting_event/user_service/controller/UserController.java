package com.capstone.sporting_event.user_service.controller;

import com.capstone.sporting_event.user_service.dto.UserDisplayDTO;
import com.capstone.sporting_event.user_service.dto.UserRequestDTO;
import com.capstone.sporting_event.user_service.dto.UserMapper;
import com.capstone.sporting_event.user_service.models.UserResponse;
import com.capstone.sporting_event.user_service.models.UserResponseWithBody;
import com.capstone.sporting_event.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.capstone.sporting_event.user_service.constants.Mapping.VERSION_1;
import static com.capstone.sporting_event.user_service.constants.MessageMap.*;


@RestController
@RequestMapping(value=VERSION_1)
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/{userId}")
    public UserDisplayDTO getUser(@PathVariable int userId) {
        return userMapper.toDisplayDTO(userService.getUser(userId));
    }


    @GetMapping("/users")
    public List<UserDisplayDTO> getUsers() {
        return userService.getAllUsers().stream().map(user -> userMapper.toDisplayDTO(user)).collect(Collectors.toList());
    }

    @PostMapping("register/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseWithBody> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO){


        UserResponseWithBody saveUserResponse = UserResponseWithBody.builder()
                .status(201)
                .message(SAVE_USER_SUCCESS)
                .body(userMapper.toDisplayDTO(userService.saveUser(userRequestDTO)))
                .build();

        return new ResponseEntity<>(saveUserResponse, HttpStatus.CREATED);
    }

    @PostMapping("register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseWithBody> addAdmin(@Valid @RequestBody UserRequestDTO userRequestDTO){


        UserResponseWithBody saveUserAdminResponse = UserResponseWithBody.builder()
                .status(201)
                .message(SAVE_USER_SUCCESS)
                .body(userMapper.toDisplayDTO(userService.saveUserAdmin(userRequestDTO)))
                .build();

        return new ResponseEntity<>(saveUserAdminResponse, HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseWithBody> updateUser(@PathVariable int userId, @Valid @RequestBody UserRequestDTO userRequestDTO){

        UserResponseWithBody updateUserResponse = UserResponseWithBody.builder()
                .status(204)
                .message(UPDATE_USER_SUCCESS)
                .body(userMapper.toDisplayDTO(userService.updateUser(userId, userRequestDTO)))
                .build();

        return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserResponse> deleteUser(@PathVariable("userId") int userId){

        userService.deleteUser(userId);

        UserResponse deleteUserResponse = UserResponse.builder()
                .status(202)
                .message(DELETE_USER_SUCCESS)
                .build();

        return new ResponseEntity<>(deleteUserResponse, HttpStatus.ACCEPTED);
    }

}

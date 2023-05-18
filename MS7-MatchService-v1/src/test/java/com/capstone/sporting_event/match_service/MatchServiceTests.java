package com.capstone.sporting_event.match_service;

import com.capstone.sporting_event.user_service.constants.Role;
import com.capstone.sporting_event.user_service.dto.UserDisplayDTO;
import com.capstone.sporting_event.user_service.dto.UserMapper;
import com.capstone.sporting_event.user_service.dto.UserRequestDTO;
import com.capstone.sporting_event.user_service.entity.User;
import com.capstone.sporting_event.user_service.exceptions.UserAlreadyExistException;
import com.capstone.sporting_event.user_service.exceptions.UserNotFoundException;
import com.capstone.sporting_event.user_service.repository.UserRepository;
import com.capstone.sporting_event.user_service.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MatchServiceTests {

    @Mock
    private UserRepository userRepo;

    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;



    private User userEntity;


    @BeforeEach
    public void setup(){

        userEntity = User.builder()
                .userName("jayson")
                .password("testpassword")
                .email("jsoncon@gmail.com")
                .role(Role.USER)
                .build();
    }



    @Test
    void should_Be_Able_To_Get_User()
    {
        UserDisplayDTO expected = UserDisplayDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .role("USER").build();

        when(userRepo.findById(anyInt())).thenReturn(Optional.ofNullable(userEntity));
        when(userMapper.toDisplayDTO(any(User.class))).thenReturn(expected);

        UserDisplayDTO userDTO = userMapper.toDisplayDTO(userService.getUser(1));

        assertNotNull(userDTO);
        assertEquals(expected.getUserName(), userDTO.getUserName());
        assertEquals(expected.getEmail(), userDTO.getEmail());
        assertEquals(expected.getRole(), userDTO.getRole());

    }

    @Test
    void should_Be_Able_To_Get_All_User() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();

        when(userRepo.findAll()).thenReturn(List.of(userEntity));

        List<User> users = userService.getAllUsers();

        assertEquals(1,users.size());
        assertEquals(userEntity,users.get(0));


    }
    @Test
    void should_Throw_User_Not_Found_Exception()
    {

       when(userRepo.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {

            userService.getUser(1);

        });
    }

    @Test
    void should_Be_Able_To_Save_User()
    {
       User expected = userEntity;

        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();


        when(userRepo.save(any(User.class))).thenReturn(userEntity);
        when(userMapper.toEntityForUser(any(UserRequestDTO.class))).thenReturn(expected);

        User savedUser = userService.saveUser(userRequest);

        assertNotNull(savedUser);
        assertEquals(expected.getUserName(), userRequest.getUserName());
        assertEquals(expected.getEmail(), userRequest.getEmail());
        assertEquals(expected.getPassword(), userRequest.getPassword());

    }

    @Test
    void should_Be_Able_To_Save_UserAdmin()
    {
        User expected = userEntity;

        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();


        when(userRepo.save(any(User.class))).thenReturn(userEntity);
        when(userMapper.toEntityForAdmin(any(UserRequestDTO.class))).thenReturn(expected);


        User savedUser = userService.saveUserAdmin(userRequest);


        assertNotNull(savedUser);
        assertEquals(expected.getUserName(), userRequest.getUserName());
        assertEquals(expected.getEmail(), userRequest.getEmail());
        assertEquals(expected.getPassword(), userRequest.getPassword());

    }

    @Test
    void should_Not_Add_User_If_Already_Exists_Using_UserName() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();

            when(userRepo.findByUserName(userRequest.getUserName())).thenReturn(Optional.ofNullable(userEntity));

           Exception exception = assertThrows(UserAlreadyExistException.class, () -> {

                userService.saveUser(userRequest);

            });

        String expectedMessage = "com.capstone.sporting_event.user_service.exceptions.UserAlreadyExistException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }

    @Test
    void should_Not_Add_User_If_Already_Exists_Using_Email() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();

        when(userRepo.findByEmail(userRequest.getEmail())).thenReturn(Optional.ofNullable(userEntity));

        Exception exception = assertThrows(UserAlreadyExistException.class, () -> {

            userService.saveUser(userRequest);

        });

        String expectedMessage = "com.capstone.sporting_event.user_service.exceptions.UserAlreadyExistException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }

    @Test
    void should_Not_Add_UserAdmin_If_Already_Exists_Using_UserName() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();

        when(userRepo.findByUserName(userRequest.getUserName())).thenReturn(Optional.ofNullable(userEntity));

        Exception exception = assertThrows(UserAlreadyExistException.class, () -> {

            userService.saveUserAdmin(userRequest);

        });

        String expectedMessage = "com.capstone.sporting_event.user_service.exceptions.UserAlreadyExistException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }

    @Test
    void should_Not_Add_UserAdmin_If_Already_Exists_Using_Email() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();

        when(userRepo.findByEmail(userRequest.getEmail())).thenReturn(Optional.ofNullable(userEntity));

        Exception exception = assertThrows(UserAlreadyExistException.class, () -> {

            userService.saveUserAdmin(userRequest);

        });

        String expectedMessage = "com.capstone.sporting_event.user_service.exceptions.UserAlreadyExistException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }

    @Test
    void should_Be_Able_To_Update_User() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();

        User expectedUser = User.builder()
                .userName("updatedUser")
                .email("update@email.com")
                .password("updatedPassword")
                .build();

        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(userEntity));
        when(userRepo.save(any(User.class))).thenReturn(expectedUser);


        User actualUser = userService.updateUser(1,userRequest);


        assertNotNull(actualUser);

        assertEquals(expectedUser.getUserName(),actualUser.getUserName());
        assertEquals(expectedUser.getEmail(),actualUser.getEmail());
        assertEquals(expectedUser.getPassword(),actualUser.getPassword());


    }

    @Test
    void should_Throw_User_Not_Found_In_Update_User() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();


        when(userRepo.findById(1)).thenReturn(Optional.empty());



        Exception exception = assertThrows(UserNotFoundException.class, () -> {

            User actualUser = userService.updateUser(1,userRequest);

        });

        String expectedMessage = "com.capstone.sporting_event.user_service.exceptions.UserNotFoundException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }


    @Test
    void should_Be_Able_To_Delete_User() {
        UserRequestDTO userRequest = UserRequestDTO.builder()
                .userName("jayson")
                .email("jsoncon@gmail.com")
                .password("testpassword")
                .build();


        when(userRepo.findById(1)).thenReturn(Optional.ofNullable(userEntity));

        userService.deleteUser(1);

        verify(userRepo).save(any(User.class));


    }

    @Test
    void should_Throw_User_Not_Found_In_Delete_User() {


        when(userRepo.findById(1)).thenReturn(Optional.empty());



        Exception exception = assertThrows(UserNotFoundException.class, () -> {

            userService.deleteUser(1);

        });

        String expectedMessage = "com.capstone.sporting_event.user_service.exceptions.UserNotFoundException";
        String actualMessage = exception.toString();

        Assertions.assertEquals(expectedMessage,actualMessage);



    }



    }



package com.capstone.sporting_event.tournament_service;

import com.capstone.sporting_event.user_service.constants.Role;
import com.capstone.sporting_event.user_service.entity.User;
import com.capstone.sporting_event.user_service.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)

class TournamentEntityTests {


    int id = 1;
    String userName = "jayson";
    String email = "jconcepcion@gmail.com";
    boolean active = true;
    String password = "testpassword";
    Role role = Role.USER;

    @Autowired
    private UserRepository userRepo;


    // TEST GETTERS,SETTERS

    @Test
    void test_Getters_And_Setters(){


        User user = new User();

        user.setUserId(id);
        user.setUserName(userName);
        user.setEmail(email);
        user.setActive(active);
        user.setPassword(password);
        user.setRole(role);

        String toString = user.toString();

        assertEquals(user.getUserId(),id);
        assertEquals(user.getUserName(),userName);
        assertEquals(user.getEmail(),email);
        assertEquals(user.getPassword(),password);
        assertEquals(user.getRole(),role);
        assertEquals(user.isActive(),active);
        assertNotNull(toString);

    }

    @Test
    void test_Builder(){


        User user = User.builder()
                .userId(id)
                .userName(userName)
                .email(email)
                .isActive(active)
                .password(password)
                .role(role)
                .build();

        String toString = user.toString();
        assertNotNull(toString);

        assertEquals(user.getUserId(),id);
        assertEquals(user.getUserName(),userName);
        assertEquals(user.getEmail(),email);
        assertEquals(user.getPassword(),password);
        assertEquals(user.getRole(),role);
        assertEquals(user.isActive(),active);

    }



    //TEST CONSTRAINTS

    @Test
    void should_Throw_Exception_If_UserName_Is_Empty(){
            User user = User.builder()
                    .userName(null)
                    .email("jconcep@gmail.com")
                    .password("testpassword")
                    .role(Role.USER)
                    .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            userRepo.saveAndFlush(user);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [user_name\" of relation \"user]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void should_Throw_Exception_If_Email_Is_Empty(){
        User user = User.builder()
                .userName("jconcepcion")
                .email(null)
                .password("testpassword")
                .role(Role.USER)
                .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            userRepo.saveAndFlush(user);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [email\" of relation \"user]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void should_Throw_Exception_If_Password_Is_Empty(){
        User user = User.builder()
                .userName("jconcepcion")
                .email("jconcepcion@gmail.com")
                .password(null)
                .role(Role.USER)
                .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            userRepo.saveAndFlush(user);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [password\" of relation \"user]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);

    }


    @Test
    void should_Throw_Exception_If_Role_Is_Empty(){
        User user = User.builder()
                .userName("jconcepcion")
                .email("jconcepcion@gmail.com")
                .password("testpassword")
                .role(null)
                .build();

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            userRepo.saveAndFlush(user);
        });


        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [role\" of relation \"user]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
       String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void should_Throw_Exception_If_UserName_Is_Not_Unique(){

        User user1 = User.builder()
                .userName("jconcepcion")
                .email("jconcepcion@gmails.com")
                .password("testpassword")
                .role(Role.USER)
                .build();

        User user2 = User.builder()
                .userName("jconcepcion")
                .email("jconcepcion@gmailq.com")
                .password("testpassword")
                .role(Role.USER)
                .build();

        userRepo.saveAndFlush(user1);

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            userRepo.saveAndFlush(user2);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [uk_lqjrcobrh9jc8wpcar64q1bfh]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);

    }

    @Test
    void should_Throw_Exception_If_Email_Is_Not_Unique(){

        User user1 = User.builder()
                .userName("jconcepcion123")
                .email("jconcepcion@gmail.com")
                .password("testpassword")
                .role(Role.USER)
                .build();

        User user2 = User.builder()
                .userName("jconcepcion123")
                .email("jconcepcion@gmail.com")
                .password("testpassword")
                .role(Role.USER)
                .build();

        userRepo.saveAndFlush(user1);

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            userRepo.saveAndFlush(user2);
        });

        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [uk_ob8kqyqqgmefl0aco34akdtpe]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);

    }

}

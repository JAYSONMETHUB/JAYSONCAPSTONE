package com.capstone.sporting_event.user_service.repository;

import com.capstone.sporting_event.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName (String userName);

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
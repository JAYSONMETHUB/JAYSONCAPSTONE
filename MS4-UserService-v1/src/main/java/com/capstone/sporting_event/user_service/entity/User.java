package com.capstone.sporting_event.user_service.entity;

import com.capstone.sporting_event.user_service.constants.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user",  schema = "user_schema")
@Where(clause="is_active=true")
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String userName;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "ROLE",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false,insertable = false)
    @JsonIgnore
    private boolean isActive;



}
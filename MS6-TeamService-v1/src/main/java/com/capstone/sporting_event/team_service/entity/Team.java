package com.capstone.sporting_event.team_service.entity;

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
@Table(name = "team",  schema = "team_schema")
@Where(clause="is_active=true")
public class Team {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAM_ID")
    private int teamId;

    @Column(name = "TEAM_NAME", nullable = false, unique = true)
    private String teamName;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false,insertable = false)
    @JsonIgnore
    private boolean isActive;

}
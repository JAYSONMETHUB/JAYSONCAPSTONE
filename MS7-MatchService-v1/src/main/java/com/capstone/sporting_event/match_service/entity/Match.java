package com.capstone.sporting_event.match_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Match",  schema = "match_schema")
@Where(clause="is_active=true")
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MATCH_ID")
    private int matchId;


    @Column(name = "FIELD_ID", nullable = false)
    private int fieldId;


    @Column(name = "TOURNAMENT_ID", nullable = false)
    private int tournamentId;

    @Column(name = "TEAMS_ID", nullable = false)
    private String teamsId;

    @Column(name = "date_time", nullable = false)
    private Date dateTime;




    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false,insertable = false)
    @JsonIgnore
    private boolean isActive;

}
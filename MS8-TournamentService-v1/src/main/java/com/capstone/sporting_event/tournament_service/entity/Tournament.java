package com.capstone.sporting_event.tournament_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tournament",  schema = "tournament_schema")
@Where(clause="is_active=true")
public class Tournament {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOURNAMENT_ID")
    private int tournamentId;

    @Column(name = "TOURNAMENT_NAME", nullable = false, unique = true)
    private String tournamentName;


    @Column(name = "SPORTS_CATEGORY", nullable = false)
    private String sportsCategory;

    @Column(name = "TOURNAMENT_STYLE", nullable = false)
    private String tournamentStyle;



    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false,insertable = false)
    @JsonIgnore
    private boolean isActive;

}
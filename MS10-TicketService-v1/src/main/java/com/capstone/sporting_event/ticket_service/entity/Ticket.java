package com.capstone.sporting_event.ticket_service.entity;

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
@Table(name = "Ticket",  schema = "ticket_schema")
@Where(clause="is_active=true")
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TICKET_ID")
    private int ticketId;

    @Column(name = "CUSTOMER_NAME", nullable = false)
    private String customerName;


    @Column(name = "MATCH_ID", nullable = false)
    private int matchId;

    @Column(name = "TICKET_PRICE", nullable = false)
    private float ticketPrice;



    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false,insertable = false)
    @JsonIgnore
    private boolean isActive;

}
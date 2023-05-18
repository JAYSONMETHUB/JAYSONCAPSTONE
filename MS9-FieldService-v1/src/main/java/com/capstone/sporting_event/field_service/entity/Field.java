package com.capstone.sporting_event.field_service.entity;

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
@Table(name = "Field",  schema = "field_schema")
@Where(clause="is_active=true")
public class Field {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIELD_ID")
    private int fieldId;

    @Column(name = "FIELD_NAME", nullable = false, unique = true)
    private String fieldName;


    @Column(name = "FIELD_ADDRESS", nullable = false)
    private String fieldAddress;

    @Column(name = "capacity", nullable = false)
    private int capacity;


    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false,insertable = false)
    @JsonIgnore
    private boolean isActive;

}
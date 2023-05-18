package com.capstone.sporting_event.field_service.repository;

import com.capstone.sporting_event.field_service.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {

    Optional<Field> findByFieldNameIs(String fieldName);


}